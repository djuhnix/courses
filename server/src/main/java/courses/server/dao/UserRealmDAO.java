package courses.server.dao;

import courses.server.entities.User;
import courses.server.security.RolesEnum;
import jakarta.persistence.NoResultException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * In Apache Shiro's terminology, a Realm is a DAO that points to a store of user credentials needed for authentication and authorization.
 */
public class UserRealmDAO extends JdbcRealm {

    private final UserDAO userDAO;

    public UserRealmDAO() {
        super();
        userDAO = new UserDAO();

        // -- credential matcher
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
        setCredentialsMatcher(credentialsMatcher);

        setSaltStyle(SaltStyle.COLUMN);

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        User user;
        try {
            user = userDAO.findByEmail(uToken.getUsername());
        } catch (NoResultException e) {
            throw new UnknownAccountException("user not found");
        }
        if(uToken.getUsername() == null
                || uToken.getUsername().isEmpty()
                || user == null) {
            throw new UnknownAccountException("username not found!");
        }
        ByteSource salt = ByteSource.Util.bytes(Base64.decode(user.getSalt()));

        return new SimpleAuthenticationInfo(
                uToken.getUsername(),
                user.getPasswordHash(),
                salt,
                getName()
        );
    }

    @Override
    protected String getSaltForUser(String username) {
        return userDAO.findUserSaltByEmail(username);
    }

    private String[] getPasswordForUser(Connection conn, String username) {
        return new String[]{
                userDAO.findUserPasswordByEmail(username),
                this.getSaltForUser(username)
        };
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //TODO security doGetAuthorizationInfo
        return super.doGetAuthorizationInfo(principals);
    }

    @Override
    protected Set<String> getRoleNamesForUser(Connection conn, String username) {
        HashSet<String> rolesNames = new HashSet<>();
        rolesNames.add(
                getUser(username).getRole().getRoleName()
        );
        return rolesNames;
    }

    @Override
    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) {
        HashSet<String> permissions = new HashSet<>();
        //User user = getUser(username);
        roleNames.forEach(x -> permissions.addAll(RolesEnum.valueOf(x).getPermissionsSet()));
        return permissions;
    }

    private User getUser(String username) {
        User user;
        try {
            user = userDAO.findByEmail(username);
        } catch (NoResultException e) {
            throw new UnknownAccountException("user not found");
        }
        return user;
    }
}
