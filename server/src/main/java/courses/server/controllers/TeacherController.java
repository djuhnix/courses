package courses.server.controllers;

import courses.server.dao.AbstractDAO;
import courses.server.entities.Teacher;
import courses.server.security.Password;
import courses.utils.ActionEnum;
import courses.utils.DefaultData;
import jakarta.persistence.NoResultException;

import java.util.List;

public class TeacherController extends AbstractController<Teacher> {

    public TeacherController() {
        super(new AbstractDAO<>() {
            @Override
            public List<Teacher> findAll() {
                return this.findAll(Teacher.class);
            }
            @Override
            public Teacher findById(int id) {
                return this.findById(id, Teacher.class);
            }
        });
    }

    @Override
    public Teacher read(int id) {
        Teacher result = null;
        if (isUserAdmin()) {
            try {
                result = dao.findById(id);
            } catch (NoResultException ignored) {
            }
        }
        return result;
    }

    @Override
    public int post(DefaultData<?> object) throws IllegalAccessException {
        Teacher teacher = Teacher.getFrom(object.getObject());
        int id;
        if (object.getAction() != ActionEnum.SIGN_IN && !isUserAdmin()) {
            throw new IllegalAccessException("Unable to read data : lacks of permission");
        }
        Password.saveHashedPassword(teacher, teacher.getPassword());
        dao.save(teacher);
        id = teacher.getId();
        return id;
    }

    @Override
    public Teacher update(DefaultData<?> object) {
        Teacher upTeacher = Teacher.getFrom(object.getObject());
        if (isUserAdmin()) {
            try {
                Teacher actual = dao.findById(upTeacher.getId());
                if (!actual.equals(upTeacher)) {
                    upTeacher = dao.update(upTeacher);
                }
            } catch (NoResultException ignored) {
            }
        }
        return upTeacher;
    }

}
