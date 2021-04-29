package courses.client.app;

import java.io.Serializable;

public class Auth implements Serializable {
    public String login;
    public String mdp;

    public Auth(String login, String mdp){
        this.login= login;
        this.mdp = mdp;
    }
}
