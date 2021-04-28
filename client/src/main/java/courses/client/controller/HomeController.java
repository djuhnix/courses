package courses.client.controller;

import courses.client.manager.LoginManager;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the main application screen */
public class HomeController {
    @FXML private Button logoutButton;
    @FXML private Label  sessionLabel;

    public void initialize() {}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        sessionLabel.setText(sessionID);
        logoutButton.setOnAction(event -> loginManager.logout());
    }
}