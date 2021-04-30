package courses.server.tasks;

import courses.server.controllers.UserController;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.security.Password;
import courses.server.security.RolesEnum;
import jakarta.persistence.NoResultException;
import org.apache.shiro.subject.Subject;

public class CreateAdmin {
    public static void main(String[] args) {
        logInAndOutTest(
                new UserController(),
                createAdminUser(new UserDAO())
        );
    }
    public static User createAdminUser(UserDAO dao) {
        User user = new User();
        user.setNom("nom");
        user.setPrenom("prenom");
        user.setEmail("admin");
        user.setRole(RolesEnum.ADMIN);
        user.setPassword("pass");
        Password.saveHashedPassword(user, user.getPassword());
        try {
            user = dao.findByEmail(user.getEmail());
            System.out.println("user fetched");
        } catch (NoResultException e) {
            dao.save(user);
            System.out.println("user saved");
        }
        return user;
    }

    public static void logInAndOutTest(UserController controller, User user) {
        System.out.println("Log in test ---");
        Subject currentUser = controller.logUser(user.getEmail(), user.getPassword());
        if (currentUser != null && currentUser.isAuthenticated()) {
            System.out.println("user logged in");
            if (currentUser.hasRole(RolesEnum.ADMIN.name())) {
                System.out.println("I'm an admin");
            }
            user = (User) currentUser.getSession().getAttribute("user");
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
