package courses.server;

//import courses.utils.security;
import courses.server.controllers.UserController;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.security.Password;
import courses.server.security.RolesEnum;
import org.apache.shiro.subject.Subject;

public class Main {
    public static void main(String[] args) {
        //TODO server main

        UserDAO dao = new UserDAO();
        User user = new User();
        user.setNom("nom");
        user.setPrenom("prenom");
        user.setEmail("email");
        user.setRole(RolesEnum.ADMIN);
        Password.saveHashedPassword(user, "password");
        dao.save(user);
        System.out.println("user saved");
        UserController controller = new UserController();
        Subject currentUser = controller.logUser("user", "password");
        System.out.println("user logged in");
        currentUser.logout();
        System.out.println("user logged out");
    }
}
