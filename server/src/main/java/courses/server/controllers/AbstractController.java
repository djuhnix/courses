package courses.server.controllers;

import courses.server.dao.AbstractDAO;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.manager.DefaultSecurityManager;
import courses.server.security.Password;
import courses.server.security.RolesEnum;
import courses.utils.DefaultData;
import jakarta.persistence.NoResultException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractController<T> {
    private final UserDAO userDAO;
    protected AbstractDAO<T> dao;

    AbstractController(AbstractDAO<T> dao) {
        userDAO = new UserDAO();
        this.dao = dao;
        DefaultSecurityManager.getInstance().initSecurityUtils();
    }

    public AbstractController<T> setDao(AbstractDAO<T> dao) {
        this.dao = dao;
        return this;
    }

    /**
     * Must be called after logUser method
     * @return an instance of the User class of the connected user
     */
    public User getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return (User) currentUser.getSession().getAttribute("user");
        } else {
            return null;
        }
    }

    public abstract T read(int id);
    public abstract int post(DefaultData<?> object);
    public abstract T update(DefaultData<?> object);

    public List<T> read() throws IllegalAccessException {
        List<T> result = null;
        try {
            result = dao.findAll();
        } catch (NoResultException ignored) {
        }
        return result;
    }

    public void delete(int id) {
        if (isUserAdmin()) {
            try {
                dao.delete(dao.findById(id));
            } catch (NoResultException ignored) {
            }
        }
    }

    /**
     * Log the user in the system and a session variable to fetch user info
     * @param email username
     * @param password password
     */
    public Subject logUser(String email, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token;
        if (!currentUser.isAuthenticated()) {
            token
                    = new UsernamePasswordToken(email, password);
            //token.setRememberMe(true);
            try {
                currentUser.login(token);
                currentUser.getSession().setAttribute("user", userDAO.findByEmail(email));
                currentUser.getSession().setAttribute("token", Password.getToken());
            } catch (UnknownAccountException uae) {
                currentUser = null;
                logError("Username Not Found!", uae);
                uae.printStackTrace();
            } catch (IncorrectCredentialsException ice) {
                logError("Invalid Credentials!", ice);
                ice.printStackTrace();
            } catch (LockedAccountException lae) {
                logError("Your Account is Locked!", lae);
                lae.printStackTrace();
            } catch (AuthenticationException ae) {
                currentUser = null;
                logError("Unexpected Error!", ae);
                ae.printStackTrace();
            }
        }
        return currentUser;
    }

    protected void logError(String msg, Exception e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, e);
    }
    protected void logInfo(String msg) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, msg);
    }

    protected boolean isUserAdmin() {
        return SecurityUtils.getSubject().hasRole(RolesEnum.ADMIN.name());
    }
}
