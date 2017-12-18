package think.near.app.controller;

import java.util.List;

import engine.model.Student;
import engine.model.StudentRepository;

public class StudentController {

	private static StudentRepository _studentRepository = StudentRepository.getInstance();
	private static StudentController _instance = null;

	public static StudentController getInstance() {
		if(_instance == null) {
			_instance = new StudentController();
		}
		return _instance;
	}

	public Student getStudent(int id) {
		return _studentRepository.getStudent(id);
	}

	public List<Student> getAll() {
		return _studentRepository.getAll();
	}

	public List<Student> findStudents(String query) {
		return _studentRepository.findStudents(query);
	}
}
