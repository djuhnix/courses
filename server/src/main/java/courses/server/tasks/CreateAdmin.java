package courses.server.tasks;

import courses.server.controllers.UserController;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.security.Password;
import courses.server.security.RolesEnum;
import org.apache.shiro.subject.Subject;

public class CreateAdmin {
    public static void main(String[] args) {
        createAdminUser(new UserDAO());
        logInAndOutTest(new UserController());
    }
    public static void createAdminUser(UserDAO dao) {
        User user = new User();
        user.setNom("nom");
        user.setPrenom("prenom");
        user.setEmail("admin");
        user.setRole(RolesEnum.ADMIN);
        Password.saveHashedPassword(user, "pass");
        dao.save(user);
        System.out.println("user saved");
    }

    public static void logInAndOutTest(UserController controller) {
        System.out.println("Log in test ---");
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
