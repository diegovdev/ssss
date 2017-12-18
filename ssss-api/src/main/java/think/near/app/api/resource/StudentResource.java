package think.near.app.api.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import engine.model.entity.Student;
import think.near.app.api.util.SimpleResponse;
import think.near.app.controller.StudentController;
import think.near.app.exception.OperationException;


@Path("/student/")
public class StudentResource {

	private StudentController _studentController = StudentController.getInstance();

	@GET
	@Produces("application/json")
	public List<Student> listStudents(@QueryParam("q") String query) {
		if (query == null || query.isEmpty()) {
			return _studentController.getAll();
		} else {
			return _studentController.findStudents(query.toLowerCase());
		}
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Student getStudent(@PathParam("id") int studentId) throws OperationException {
		return _studentController.getStudent(studentId);
	}

	@POST
	@Path("/")
	@Produces("application/json")
    @Consumes("application/json")
	public Student createStudent(Student s) throws OperationException {
		_studentController.createStudent(s);
		return s;
	}

	@POST
	@Path("/{id}")
	@Produces("application/json")
    @Consumes("application/json")
	public Student updateStudent(@PathParam("id") int id, Student s) throws OperationException {
		_studentController.updateStudent(id, s);
		return s;
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public SimpleResponse deleteStudent(@PathParam("id") int id) throws OperationException {
		_studentController.deleteStudent(id);
		return new SimpleResponse("success", "Deleted student with id " + id);
	}

}
