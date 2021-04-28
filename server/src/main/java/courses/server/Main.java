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
        createAdminUser(new UserDAO());
        logInAndOutTest(new UserController());
    }

    public static void createAdminUser(UserDAO dao) {
        User user = new User();
        user.setNom("nom");
        user.setPrenom("prenom");
        user.setEmail("email");
        user.setRole(RolesEnum.ADMIN);
        Password.saveHashedPassword(user, "password");
        dao.save(user);
        System.out.println("user saved");
    }

    public static void logInAndOutTest(UserController controller) {
        Subject currentUser = controller.logUser("email", "password");
        if (currentUser.isAuthenticated()) {
            System.out.println("user logged in");
            if (currentUser.hasRole(RolesEnum.ADMIN.name())) {
                System.out.println("I'm an admin");
            }
            User user = (User) currentUser.getSession().getAttribute("user");
            System.out.println("User lastname : " + user.getNom());
        } else {
            System.out.println("log in failed");
            System.exit(0);
        }
        currentUser.logout();
        //if (currentUser.is)
        System.out.println("user logged out");
    }
}
