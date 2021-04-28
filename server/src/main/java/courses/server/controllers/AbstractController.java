package courses.server.controllers;

import com.hazelcast.client.impl.ClientEndpoint;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.manager.DefaultSecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

public class AbstractController {
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

    /**
     * Log the user in the system and a session variable to fetch user info
     * @param email username
     * @param password password
     */
    public Subject logUser(String email, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token
                    = new UsernamePasswordToken(email, password);
            //token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.err.println("Username Not Found!");
                uae.printStackTrace();
            } catch (IncorrectCredentialsException ice) {
                System.err.println("Invalid Credentials!");
                ice.printStackTrace();
            } catch (LockedAccountException lae) {
                System.err.println("Your Account is Locked!");
                lae.printStackTrace();
            } catch (AuthenticationException ae) {
                System.err.println("Unexpected Error!");
                ae.printStackTrace();
            }
        }
        currentUser.getSession().setAttribute("user", userDAO.findByEmail(email));
        return currentUser;
    }

}
