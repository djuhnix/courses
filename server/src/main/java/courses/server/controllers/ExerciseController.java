package courses.server.controllers;

import courses.server.dao.AbstractDAO;
import courses.server.entities.Exercise;
import courses.utils.DefaultData;

import java.util.List;

public class ExerciseController extends AbstractController<Exercise> {

    public ExerciseController() {
        super(new AbstractDAO<>() {
            @Override
            public List<Exercise> findAll() {
                return this.findAll(Exercise.class);
            }

            @Override
            public Exercise findById(int id) {
                return this.findById(id, Exercise.class);
            }
        });
    }

    @Override
    public Exercise read(int id) {
        return null;
    }

    @Override
    public int post(DefaultData<?> object) {
        return 0;
    }

    @Override
    public Exercise update(DefaultData<?> object) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
