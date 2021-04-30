package courses.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainStudentController {
    @FXML public Label welcome;

    public void initialize() {
        //trouver un moyen de récuperer l'user de la session
        welcome.setText("Bienvenue sur Mood");
    }
}
