package courses.client.manager;

import courses.client.api.ClientExchanges;
import javafx.scene.Scene;

public abstract class AbstractManager {
    protected Scene scene;
    protected ClientExchanges clientExchanges;

    protected AbstractManager(Scene scene, ClientExchanges exchanges) {
        this.scene = scene;
        clientExchanges = exchanges;
    }

    public ClientExchanges getClientExchanges() {
        return clientExchanges;
    }
}
