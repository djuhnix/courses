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
/*
        UserDAO dao = new UserDAO();
        User user = new User();
        user.setNom("nom");
        user.setPrenom("prenom");
        user.setEmail("email");
        user.setRole(RolesEnum.ADMIN);
        Password.saveHashedPassword(user, "password");
        dao.save(user);
        System.out.println("user saved");

 */
        UserController controller = new UserController();
        Subject currentUser = controller.logUser("email", "password");
        if (currentUser.isAuthenticated()) {
            System.out.println("user logged in");
            if (currentUser.hasRole(RolesEnum.ADMIN.name())) {
                System.out.println("I'm an admin");
            }
        } else {
            System.out.println("log in failed");
            System.exit(0);
        }
        currentUser.logout();
        //if (currentUser.is)
        System.out.println("user logged out");
    }
}
