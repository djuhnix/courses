package courses.client.controller;

import courses.client.api.ClientExchanges;
import courses.client.manager.AbstractManager;
import courses.client.manager.LoginManager;
import courses.client.manager.SignInManager;
import courses.utils.DefaultData;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SignInController extends AbstractController {
    @FXML public TextField ine;
    @FXML public TextField mail;
    @FXML public TextField address1;
    @FXML public TextField address2;
    @FXML public TextField city;
    @FXML public PasswordField password;
    @FXML public PasswordField confirmPass;
    @FXML public TextField cp;
    @FXML public Button submit;
    @FXML public RadioButton teacher;
    @FXML public RadioButton student;
    @FXML public Label error;

    @Override
    public void initManager(final AbstractManager manager) {
        SignInManager signInManager = (SignInManager) manager;
        submit.setOnAction(event -> {
            try {
                boolean signInStatus = signIn(signInManager);
                if (signInStatus && teacher.isSelected()){
                    signInManager.showRoleView('T');
                }
            } catch (Exception e) {
                error.setText("Une erreur est survenue");
                e.printStackTrace();
            }
        });
    }

    private boolean signIn(SignInManager manager) throws IOException {
        ClientExchanges exchanges = manager.getClientExchanges();
        DefaultData<?> response = null;
        if (!password.getText().equals(confirmPass.getText())) {
            error.setText("Les mot de passes ne sont pas identique");
        } else {
            if (teacher.isSelected()) {
                response = exchanges.registrationTeacher(
                        Integer.parseInt(ine.getText()),
                        "firstname",
                        "lastname",
                        mail.getText(),
                        "123456789",
                        address1.getText(),
                        address2.getText(),
                        city.getText(),
                        cp.getText(),
                        password.getText()
                );
            } else if (student.isSelected()) {
                response = exchanges.registrationStudent(
                        Integer.parseInt(ine.getText()),
                        "firstname",
                        "lastname",
                        mail.getText(),
                        "123456789",
                        address1.getText(),
                        address2.getText(),
                        city.getText(),
                        cp.getText(),
                        password.getText()
                );
            }
        }
        return response != null && response.isRequestStatus();
    }
}
