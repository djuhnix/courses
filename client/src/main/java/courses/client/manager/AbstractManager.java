package courses.client.manager;

import courses.client.api.ClientExchanges;

public abstract class AbstractManager {
    protected ClientExchanges clientExchanges;

    protected AbstractManager(ClientExchanges exchanges) {
        clientExchanges = exchanges;
    }

    public ClientExchanges getClientExchanges() {
        return clientExchanges;
    }
}
