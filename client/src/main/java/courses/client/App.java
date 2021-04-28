package courses.client;

import courses.client.manager.LoginManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
//fx:controller="courses.client.controller.Home"
public class App extends Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Application started ! ");
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mood - Home");
        /*
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(App.class.getResource("HomeView.fxml"))
        );
         */
        Scene scene = new Scene(new StackPane());
        LoginManager loginManager = new LoginManager(scene);
        loginManager.showLoginScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Application stopped ! ");
    }
}
