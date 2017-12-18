package engine.model.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import engine.model.entity.Class;
import engine.model.entity.Student;

public class DataStore {

	private String _connString;
	private int _dbAutoIncrementId = 0;
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

	public boolean updateStudent(Integer studentId, Student aStudent) {
		aStudent.setId(studentId);
		if(this._lookupStudentById.get(studentId) != null) {
			this._deleteFromStudentList(this._studentList, studentId);
			this._studentList.add(aStudent);
			this._lookupStudentById.remove(studentId);
			this._lookupStudentById.put(studentId, aStudent);
		} else {
			return false;
		}
		return true;
	}

	public boolean deleteStudent(Integer studentId) {
		if(this._lookupStudentById.get(studentId) != null) {
			this._deleteFromStudentList(this._studentList, studentId);
			this._lookupStudentById.remove(studentId);
		} else {
			return false;
		}

		ArrayList<String> studentClasses = this._assignmentsByStudentId.get(studentId);
		if(studentClasses != null) {
			for (String classCode : studentClasses) {
				ArrayList<Integer> list = this._assignmentsByClassCode.get(classCode);
				this._deleteFromIntegerList(list, studentId);
			}
			this._assignmentsByStudentId.remove(studentId);
		}
		return true;
	}



	public String insertClass(Class aClass) {
		this._classList.add(aClass);
		this._lookupClassByCode.put(aClass.getCode(), aClass);
		
		return aClass.getCode();
	}

	public boolean updateClass(String classCode, Class aClass) {
		aClass.setCode(classCode);
		if(this._lookupClassByCode.get(classCode) != null) {
			this._deleteFromClassList(this._classList, classCode);
			this._classList.add(aClass);
			this._lookupClassByCode.remove(classCode);
			this._lookupClassByCode.put(classCode, aClass);
		} else {
			return false;
		}
		return true;
	}

	public boolean deleteClass(String classCode) {
		if(this._lookupClassByCode.get(classCode) != null) {
			this._deleteFromClassList(this._classList, classCode);
			this._lookupClassByCode.remove(classCode);
		} else {
			return false;
		}

		ArrayList<Integer> classStudents = this._assignmentsByClassCode.get(classCode);
		if(classStudents != null) {
			for (Integer studentId : classStudents) {
				ArrayList<String> list = this._assignmentsByStudentId.get(studentId);
				this._deleteFromStringList(list, classCode);
			}
			this._assignmentsByClassCode.remove(classCode);
		}
		return true;
	}



	public boolean assignStudentToClass(Integer studentId, String classCode) {
		System.console().writer().println("assigning student: " + studentId + " to class: " + classCode);
		//add to _lookupClassesByStudentId
		if(!this._assignmentsByStudentId.containsKey(studentId)) {
			this._assignmentsByStudentId.put(studentId, new ArrayList<String>());
		}
		this._assignmentsByStudentId.get(studentId).add(classCode);

		//add to _lookupStudentsByClassCode
		if(!this._assignmentsByClassCode.containsKey(classCode)) {
			this._assignmentsByClassCode.put(classCode, new ArrayList<Integer>());
		}
		this._assignmentsByClassCode.get(classCode).add(studentId);

		return true;
	}



	public ArrayList<Class> queryAllClasses() {
		return this._classList;
	}
	
	public ArrayList<Student> queryAllStudents() {
		return this._studentList;
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
		if (studentIds != null) {
			for (Integer studentId : studentIds) {
				resultList.add(this._lookupStudentById.get(studentId));
			}
		}
		return resultList;
	}
	
	public ArrayList<Class> queryAssignmentsByStudentId(Integer studentId) {
		ArrayList<String> classCodes = this._assignmentsByStudentId.get(studentId);
		ArrayList<Class> resultList = new ArrayList<Class>();
		if (classCodes != null) {
			for (String classCode : classCodes) {
				resultList.add(this._lookupClassByCode.get(classCode));
			}
		}
		return resultList;
	}	



	private void _deleteFromStudentList(ArrayList<Student> list, Integer studentId) {
		Iterator<Student> iter = list.iterator();
		while (iter.hasNext())  {
			Student student = iter.next();
			if(student.getId() == studentId) {
				iter.remove();
			}
		}
	}
	private void _deleteFromIntegerList(ArrayList<Integer> list, Integer studentId) {
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext())  {
			Integer id = iter.next();
			if(id == studentId) {
				iter.remove();
			}
		}
	}
	private void _deleteFromClassList(ArrayList<Class> list, String classCode) {
		Iterator<Class> iter = list.iterator();
		while (iter.hasNext())  {
			Class aclass = iter.next();
			if(aclass.getCode().equals(classCode)) {
				iter.remove();
			}
		}
	}
	private void _deleteFromStringList(ArrayList<String> list, String classCode) {
		Iterator<String> iter = list.iterator();
		while (iter.hasNext())  {
			String code = iter.next();
			if(code == classCode) {
				iter.remove();
			}
		}
	}

}
