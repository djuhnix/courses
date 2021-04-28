package courses.client.controller;

import courses.client.manager.LoginManager;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the login screen */
public class LoginController {
    @FXML public Button submit;
    @FXML public Label error;
    @FXML public Button register;
    @FXML public Button quit;
    @FXML private TextField user;
    @FXML private TextField password;

    public void initialize() {}

    public void initManager(final LoginManager loginManager) {
        submit.setOnAction(
                event -> {
                    String sessionID = authorize();
                    if (sessionID != null) {
                        loginManager.authenticated(sessionID);
                    }
                });
    }

    /**
     * Check authorization credentials.
     *
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize() {
        return
                "open".equals(user.getText()) && "sesame".equals(password.getText())
                        ? generateSessionID()
                        : null;
    }

    private static int sessionID = 0;

    private String generateSessionID() {
        sessionID++;
        return "xyzzy - session " + sessionID;
    }
}
