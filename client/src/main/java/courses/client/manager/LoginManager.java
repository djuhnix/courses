package courses.client.manager;

import java.io.IOException;
import java.util.logging.*;

import courses.client.api.ClientExchanges;
import courses.client.controller.HomeController;
import courses.client.controller.LoginController;
import courses.client.controller.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

/** Manages control flow for logins */
public class LoginManager extends AbstractManager {
    public static final String SIGN_IN_FXML = "/courses/client/signIn.fxml";
    public static final String LOGIN_FXML = "/courses/client/login.fxml";
    public static final String HOME_FXML = "/courses/client/home.fxml";

    public LoginManager(Scene scene, ClientExchanges exchanges) {
        super(scene, exchanges);
        this.scene = scene;
    }

    /**
     * Callback method invoked to notify that a user has been authenticated.
     * Will show the main application screen.
     */
    public void authenticated(String token) {
        showHomeScreen(token);
    }

    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the login application screen.
     */
    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(LOGIN_FXML)
            );
            scene.setRoot(loader.load());
            LoginController controller =
                    loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, "Error : unable to show login screen", ex);
        }
    }

    private void showHomeScreen(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(HOME_FXML)
            );
            scene.setRoot(loader.load());
            HomeController controller =
                    loader.getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showRegisterView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(SIGN_IN_FXML)
            );
            scene.setRoot(loader.load());
            SignInController controller =
                    loader.getController();
            controller.initManager(new SignInManager(scene, clientExchanges));
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
