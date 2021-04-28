package courses.server.controllers;

import courses.server.manager.DefaultSecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

public class AbstractController {

    AbstractController() {
        DefaultSecurityManager.getInstance().initSecurityUtils();
    }

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
        return currentUser;
    }

}
