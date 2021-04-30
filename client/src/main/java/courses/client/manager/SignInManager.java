package courses.client.manager;

import courses.client.api.ClientExchanges;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInManager extends AbstractManager {
    public static final String STUDENT = "/courses/client/activiteStudent.fxml";
    public static final String TEACHER = "/courses/client/activiteTeacher.fxml";
    protected SignInManager(Scene scene, ClientExchanges exchanges) {
        super(scene, exchanges);
        this.scene = scene;
    }

    /**
     * @param role T or S
     */
    public void showRoleView(char role) {
        URL view;
        if (role == 'T') {
            view = getClass().getResource(TEACHER);
        } else if (role == 'S') {
            view = getClass().getResource(STUDENT);
        } else {
            throw new IllegalArgumentException("Invalid role : " + role);
        }
        FXMLLoader loader = new FXMLLoader(view);
        try {
            scene.setRoot(loader.load());
            //TODO next view
        } catch (IOException e) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, "Error : unable to show role view", e);
        }
    }
}
