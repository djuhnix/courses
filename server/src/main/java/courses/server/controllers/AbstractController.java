package courses.server.controllers;

import com.hazelcast.client.impl.ClientEndpoint;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.manager.DefaultSecurityManager;
import courses.server.security.Password;
import courses.utils.DefaultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

public abstract class AbstractController<T> {
    private final UserDAO userDAO;

    AbstractController() {
        userDAO = new UserDAO();
        DefaultSecurityManager.getInstance().initSecurityUtils();
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

    public abstract T read(Class<?> type, int id);
    public abstract T read(int id);
    public abstract boolean post(DefaultData<?> object);
    public abstract T update(DefaultData<?> object);
    public abstract void delete(int id);

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
                System.err.println("Username Not Found!");
                currentUser = null;
                uae.printStackTrace();
            } catch (IncorrectCredentialsException ice) {
                System.err.println("Invalid Credentials!");
                ice.printStackTrace();
            } catch (LockedAccountException lae) {
                System.err.println("Your Account is Locked!");
                lae.printStackTrace();
            } catch (AuthenticationException ae) {
                System.err.println("Unexpected Error!");
                currentUser = null;
                ae.printStackTrace();
            }
        }
        return currentUser;
    }
}
