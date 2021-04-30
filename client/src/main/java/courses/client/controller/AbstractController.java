package courses.client.controller;

import courses.client.manager.AbstractManager;
import courses.client.manager.LoginManager;

public abstract class AbstractController {
    public abstract void initManager(final AbstractManager manager);
}
