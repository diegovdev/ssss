package engine.model;

import java.util.ArrayList;
import java.util.List;


public class StudentRepository {

	private static StudentRepository _instance = null;

	public static StudentRepository getInstance() {
		if(_instance == null) {
			_instance = new StudentRepository();
		}
		return _instance;
	}

	public List<Student> getAll() {
		Student ale = new Student();
		ale.setId(1);
		ale.setName("Alessandro Nesta");
		ale.setEmail("ale@milanello.com");

		Student diego = new Student();
		diego.setId(2);
		diego.setName("Andrea Pirlo");
		diego.setEmail("andrea@milanello.com");

		List<Student> students = new ArrayList<Student>();
		students.add(ale);
		students.add(diego);

		return students;
	}

	public List<Student> findStudents(String query) {
		List<Student> results = new ArrayList<Student>();

		for (Student student : getAll()) {
			if (student.getName().toLowerCase().contains(query)) {
				results.add(student);
			}
		}

		return results;
	}

	public Student getStudent(int id) {
		Float a = (float) (1/0);
		Student student = new Student();
		student.setId(id);
		student.setName("Alessandro Nesta");
		student.setEmail("ale@milanello.com");

		return student;
	}

	public boolean createStudent(Student s) {
		Float a = (float) (1/0);
		return true;
	}

	public boolean updateStudent(int id, Student s) {
		Float a = (float) (1/0);
		return true;
	}

	public boolean deleteStudent(int id) {
		Float a = (float) (1/0);
		return true;
	}	
}
