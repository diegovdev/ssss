package engine.model;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public static Student getStudent(int id) {
        Student student = new Student();
        student.setId(id);
        student.setName("Alessandro Nesta");
        student.setEmail("ale@milanello.com");

        return student;
    }

    public static List<Student> getAll() {
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

    public static List<Student> findStudents(String query) {
        List<Student> results = new ArrayList<Student>();

        for (Student student : getAll()) {
            if (student.getName().toLowerCase().contains(query)) {
                results.add(student);
            }
        }

        return results;
    }
}
