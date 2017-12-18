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
import think.near.app.controller.EntityController;
import think.near.app.exception.OperationException;


@Path("/student/")
public class StudentEndpoint {

	private EntityController _entityController = EntityController.getInstance();

	@GET
	@Produces("application/json")
	public List<Student> listStudents(@QueryParam("q") String query) throws OperationException {
		if (query == null || query.isEmpty()) {
			return _entityController.getAllStudents();
		} else {
			return _entityController.findStudents(query.toLowerCase());
		}
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Student getStudent(@PathParam("id") int studentId) throws OperationException {
		return _entityController.getStudent(studentId);
	}

	@POST
	@Path("/")
	@Produces("application/json")
    @Consumes("application/json")
	public Student createStudent(Student s) throws OperationException {
		_entityController.createStudent(s);
		return s;
	}

	@POST
	@Path("/{id}")
	@Produces("application/json")
    @Consumes("application/json")
	public SimpleResponse updateStudent(@PathParam("id") int id, Student s) throws OperationException {
		boolean success = _entityController.updateStudent(id, s);
		if (success) {
			return new SimpleResponse("updated", "Updated student with id " + id);
		} else {
			return new SimpleResponse("unmodified", "No student found with id " + id);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public SimpleResponse deleteStudent(@PathParam("id") int id) throws OperationException {
		boolean success = _entityController.deleteStudent(id);
		if (success) {
			return new SimpleResponse("deleted", "Deleted student with id " + id);
		} else {
			return new SimpleResponse("unmodified", "No student found with id " + id);
		}
	}

}
