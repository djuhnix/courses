package courses.server.controllers;

import courses.server.entities.Exercise;
import courses.utils.DefaultData;

public class ExerciseController extends AbstractController<Exercise> {

    @Override
    public Exercise read(Class<?> type, int id) {
        return null;
    }

    @Override
    public Exercise read(int id) {
        return null;
    }

    @Override
    public boolean post(DefaultData<?> object) {
        return false;
    }

    @Override
    public Exercise update(DefaultData<?> object) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
