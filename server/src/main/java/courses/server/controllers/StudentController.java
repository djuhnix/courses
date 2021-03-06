package courses.server.controllers;

import courses.server.dao.AbstractDAO;
import courses.server.entities.Student;
import courses.server.security.Password;
import courses.utils.ActionEnum;
import courses.utils.DefaultData;
import jakarta.persistence.NoResultException;

import java.util.List;

public class StudentController extends AbstractController<Student> {

    public StudentController() {
        super(new AbstractDAO<>() {
            @Override
            public List<Student> findAll() {
                return this.findAll(Student.class);
            }
            @Override
            public Student findById(int id) {
                return this.findById(id, Student.class);
            }
        });
    }

    @Override
    public Student read(int id) {
        Student result = null;
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
        Student student = Student.getFrom(object.getObject());
        int id = 0;
        if (object.getAction() != ActionEnum.SIGN_IN && !isUserAdmin()) {
            throw new IllegalAccessException("Unable to read data : lacks of permission");
        }
        Password.saveHashedPassword(student, student.getPassword());
        dao.save(student);
        id = student.getId();
        return id;
    }

    @Override
    public Student update(DefaultData<?> object) {
        Student upStudent = Student.getFrom(object.getObject());
        if (isUserAdmin()) {
            try {
                Student actual = dao.findById(upStudent.getId());
                if (!actual.equals(upStudent)) {
                    upStudent = dao.update(upStudent);
                }
            } catch (NoResultException ignored) {
            }
        }
        return upStudent;
    }

}
