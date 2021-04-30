package courses.client.controller;

import courses.client.manager.AbstractManager;
import courses.client.manager.LoginManager;
import courses.utils.DefaultData;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/** Controls the login screen */
public class LoginController extends AbstractController {
    @FXML public Button submit;
    @FXML public Label error;
    @FXML public Button register;
    @FXML private TextField user;
    @FXML private TextField password;

    public void initialize() {}

    public void initManager(final AbstractManager manager) {
        LoginManager loginManager = (LoginManager) manager;
        submit.setOnAction(
                event -> {
                    String token = authorize(loginManager);
                    if (token != null) {
                        loginManager.authenticated(token);
                    } else {
                        error.setText("Une erreur est survenue, réessayez");
                    }
                });

        register.setOnAction(event -> {
            loginManager.showRegisterView();
        });
    }

    /**
     * Check authorization credentials.
     *
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize(LoginManager loginManager) {
        DefaultData<?> data = null;
        try {
            data = loginManager.getClientExchanges().initConnexion(user.getText(), password.getText());
            if (data.getMessage() != null) {
                error.setText(data.getMessage());
            }
        } catch (IOException e) {
            error.setText("Une erreur est survenue");
            e.printStackTrace();
        }
        return data != null ? data.getToken() : null;
    }

    private static int sessionID = 0;

    private String generateSessionID() {
        sessionID++;
        return "xyzzy - session " + sessionID;
    }
}
