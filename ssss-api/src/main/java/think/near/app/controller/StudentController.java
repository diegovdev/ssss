package think.near.app.controller;

import java.util.List;

import engine.model.Student;
import engine.model.StudentRepository;
import think.near.app.exception.OperationException;

public class StudentController {

	private static StudentRepository _studentRepository = StudentRepository.getInstance();
	private static StudentController _instance = null;

	public static StudentController getInstance() {
		if(_instance == null) {
			_instance = new StudentController();
		}
		return _instance;
	}

	public List<Student> getAll() {
		return _studentRepository.getAll();
	}

	public List<Student> findStudents(String query) {
		return _studentRepository.findStudents(query);
	}

	public Student getStudent(int id) throws OperationException {
		try {
			return _studentRepository.getStudent(id);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * sends 404 error with custom error message.
			 */
			throw new OperationException("Error while searching for Student");
		}
	}
	
	public boolean createStudent(Student s) throws OperationException {
		try {
			_studentRepository.createStudent(s);
			return true;
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * sends 404 error with custom error message.
			 */
			throw new OperationException("Error while creating a new Student");
		}
	}
	
	public boolean updateStudent(int id, Student s) throws OperationException {
		try {
			_studentRepository.updateStudent(id, s);
			return true;
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * sends 404 error with custom error message.
			 */
			throw new OperationException("Error while updating a Student");
		}
	}
	
	public boolean deleteStudent(int id) throws OperationException {
		try {
			_studentRepository.deleteStudent(id);
			return true;
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * sends 404 error with custom error message.
			 */
			throw new OperationException("Error while deleting a Student");
		}
	}
}
