package think.near.app.controller;

import java.util.ArrayList;
import java.util.List;

import engine.model.BusinessLogicManager;
import engine.model.entity.Class;
import engine.model.entity.Student;
import think.near.app.exception.OperationException;

public class EntityController {

	private static BusinessLogicManager _blManager = BusinessLogicManager.getInstance();
	private static EntityController _instance = null;

	public static EntityController getInstance() {
		if(_instance == null) {
			_instance = new EntityController();
		}
		return _instance;
	}

	public List<Student> getAllStudents() throws OperationException {
		try {
			return _blManager.getAllStudents();
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while searching for Student", e);
		}
	}

	public List<Student> findStudents(String query) {
		return _blManager.findStudents(query);
	}

	public Student getStudent(int id) throws OperationException {
		try {
			return _blManager.getStudent(id);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while searching for Student", e);
		}
	}
	
	public Integer createStudent(Student s) throws OperationException {
		try {
			return _blManager.createStudent(s);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while creating a new Student", e);
		}
	}
	
	public boolean updateStudent(int id, Student s) throws OperationException {
		try {
			return _blManager.updateStudent(id, s);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while updating a Student", e);
		}
	}
	
	public boolean deleteStudent(int id) throws OperationException {
		try {
			return _blManager.deleteStudent(id);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while deleting a Student", e);
		}
	}

	

	public List<Class> getAllClasses() throws OperationException {
		try {
			return _blManager.getAllClasses();
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while searching for Class", e);
		}
	}
	
	public Class getClass(String code) throws OperationException {
		try {
			return _blManager.getClass(code);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while searching for Class", e);
		}
	}
	
	public String createClass(Class s) throws OperationException {
		try {
			return _blManager.createClass(s);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while creating a new Class", e);
		}
	}
	
	public boolean updateClass(String code, Class s) throws OperationException {
		try {
			return _blManager.updateClass(code, s);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while updating a Class", e);
		}
	}
	
	public boolean deleteClass(String code) throws OperationException {
		try {
			return _blManager.deleteClass(code);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while deleting a Class", e);
		}
	}

	
	
	public boolean assignStudentToClass(int id, String code) throws OperationException {
		try {
			return _blManager.assignStudentToClass(id, code);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while assigning a Student", e);
		}
	}
	
	public ArrayList<Student> listAssignmentsByClass(String classCode) throws OperationException {
		try {
			return _blManager.listAssignmentsByClass(classCode);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while listing assignments", e);
		}
	}
	
	public ArrayList<Class> listAssignmentsByStudent(Integer studentId) throws OperationException {
		try {
			return _blManager.listAssignmentsByStudent(studentId);
		} catch (Exception e) {
			/* Handle and log internal exceptions here, user don't need to see
			 * internal exceptions so a generic OperationException is thrown which
			 * is mapped to a 404 error with a custom error message.
			 */
			throw new OperationException("Error while listing assignments", e);
		}
	}

}
