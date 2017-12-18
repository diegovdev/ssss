package engine.model.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.model.entity.Class;
import engine.model.entity.Student;

public class DataStore {

	private String _connString;
	private int _dbAutoIncrementId = -1;
	private static DataStore _instance = null;
	
	private ArrayList<Student> _studentList = new ArrayList<Student>();
	private ArrayList<Class> _classList = new ArrayList<Class>();

	private Map<Integer, Student> _lookupStudentById = new HashMap<Integer, Student>();
	private Map<String, Class> _lookupClassByCode = new HashMap<String, Class>();

	private Map<Integer, ArrayList<String>> _assignmentsByStudentId = new HashMap<Integer, ArrayList<String>>();
	private Map<String, ArrayList<Integer>> _assignmentsByClassCode = new HashMap<String, ArrayList<Integer>>();

	public static DataStore getInstance(String connString) {
		if(_instance == null) {
			_instance = new DataStore(connString);
		}
		return _instance;
	}

	protected DataStore(String connString) {
		this._connString = connString;
	}

	public int insertStudent(Student aStudent) {
		this._dbAutoIncrementId++;
		//this id would usually be returned by the database
		int newId = this._dbAutoIncrementId;

		aStudent.setId(newId);
		this._studentList.add(aStudent);
		this._lookupStudentById.put(newId, aStudent);
		
		return newId;
	}

	public boolean updateStudent(Student aStudent) {
		if(this._studentList.get(aStudent.getId()) != null) {
			this._studentList.remove(aStudent.getId());
			this._studentList.add(aStudent);
		}
		return true;
	}

	public boolean deleteStudent(Integer studentId) {
		if(this._studentList.get(studentId) != null) {
			this._studentList.remove(studentId);
		}
		
		ArrayList<String> studentClasses = this._assignmentsByStudentId.get(studentId);
		for (String classCode : studentClasses) {
			this._assignmentsByClassCode.get(classCode).remove(studentId);
		}
		this._assignmentsByStudentId.remove(studentId);
		return true;
	}

	public boolean assignStudent(Integer studentId, String classCode) {
		//add to _lookupClassesByStudentId
		if(!this._assignmentsByStudentId.containsKey(studentId))
			this._assignmentsByStudentId.put(studentId, new ArrayList<String>());
		this._assignmentsByStudentId.get(studentId).add(classCode);

		//add to _lookupStudentsByClassCode
		if(!this._assignmentsByClassCode.containsKey(classCode))
			this._assignmentsByClassCode.put(classCode, new ArrayList<Integer>());
		this._assignmentsByClassCode.get(classCode).add(studentId);

		return true;
	}
	
	public Class queryClassByCode(String classCode) {
		return this._lookupClassByCode.get(classCode);
	}
	
	public Student queryStudentById(Integer studentId) {
		return this._lookupStudentById.get(studentId);
	}
	
	public ArrayList<Student> queryAssignmentsByClassCode(String classCode) {
		ArrayList<Integer> studentIds = this._assignmentsByClassCode.get(classCode);
		ArrayList<Student> resultList = new ArrayList<Student>();
		for (Integer studentId : studentIds) {
			resultList.add(this._lookupStudentById.get(studentId));
		}
		return resultList;
	}
	
	public ArrayList<Class> queryAssignmentsByStudentId(Integer studentId) {
		ArrayList<String> classCodes = this._assignmentsByStudentId.get(studentId);
		ArrayList<Class> resultList = new ArrayList<Class>();
		for (String classCode : classCodes) {
			resultList.add(this._lookupClassByCode.get(classCode));
		}
		return resultList;
	}	
}
