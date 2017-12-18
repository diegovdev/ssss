package engine.model;

import java.util.ArrayList;
import java.util.List;

import engine.model.entity.Class;
import engine.model.entity.Student;
import engine.model.store.DataStore;


public class BusinessLogicManager {

	private static DataStore _dataStore = DataStore.getInstance("someconnstringtodatabase");
	private static BusinessLogicManager _instance = null;

	public static BusinessLogicManager getInstance() {
		if(_instance == null) {
			_instance = new BusinessLogicManager();
		}
		return _instance;
	}

	public List<Student> getAllStudents() {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryAllStudents();
	}

	public List<Student> findStudents(String query) {
		List<Student> results = new ArrayList<Student>();

		for (Student student : getAllStudents()) {
			if (student.getFirstname().toLowerCase().contains(query)) {
				results.add(student);
			}
		}

		return results;
	}

	public Student getStudent(int studentId) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryStudentById(studentId);
	}

	public Integer createStudent(Student aStudent) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.insertStudent(aStudent);
	}

	public boolean updateStudent(Integer studentId, Student aStudent) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.updateStudent(studentId, aStudent);
	}

	public boolean deleteStudent(int studentId) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.deleteStudent(studentId);
	}

	
	

	public List<Class> getAllClasses() {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryAllClasses();
	}

	public Class getClass(String classCode) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryClassByCode(classCode);
	}

	public String createClass(Class aClass) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.insertClass(aClass);
	}

	public boolean updateClass(String classCode, Class aClass) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.updateClass(classCode, aClass);
	}

	public boolean deleteClass(String classCode) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.deleteClass(classCode);
	}

	
	
	public boolean assignStudentToClass(int studentId, String classCode) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.assignStudentToClass(studentId, classCode);
	}	
	
	public ArrayList<Student> listAssignmentsByClass(String classCode) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryAssignmentsByClassCode(classCode);
	}
	
	public ArrayList<Class> listAssignmentsByStudent(Integer studentId) {
		/*
		 * do some logic here and at some point calling database
		 */
		return _dataStore.queryAssignmentsByStudentId(studentId);
	}
}

