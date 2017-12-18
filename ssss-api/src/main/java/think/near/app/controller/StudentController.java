package think.near.app.controller;

import java.util.List;

import engine.model.Student;
import engine.model.StudentRepository;

public class StudentController {

    public static Student getStudent(int id) {
    	return StudentRepository.getStudent(id);
    }

    public static List<Student> getAll() {
    	return StudentRepository.getAll();
    }

    public static List<Student> findStudents(String query) {
    	return StudentRepository.findStudents(query);
    }
}
