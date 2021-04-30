package courses.server.controllers;

import courses.server.dao.UserDAO;
import courses.utils.DefaultData;

public class UserController extends AbstractController {

    public void postUser(Object json) {
        //this.logUser(json.username, json.password);
    }

    @Override
    public Object read(Class type, int id) {
        return null;
    }

    @Override
    public Object read(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Object update(DefaultData object) {
        return null;
    }

    @Override
    public boolean post(DefaultData object) {
        return false;
    }
}
