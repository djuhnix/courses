package courses.client;

import courses.client.api.ClientExchanges;
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

    private static String server;
    private static int port;

    public static void main(String[] args) {
        System.out.println("Application started ! ");
        port = Integer.parseInt(args[0]);
        server = args[1];
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
        ClientExchanges exchanges = new ClientExchanges(server, port);
        LoginManager loginManager = new LoginManager(scene, exchanges);
        loginManager.showLoginScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Application stopped ! ");
    }
}
