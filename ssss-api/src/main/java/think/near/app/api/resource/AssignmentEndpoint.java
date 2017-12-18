package think.near.app.api.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import engine.model.entity.Class;
import engine.model.entity.Student;
import think.near.app.api.util.SimpleResponse;
import think.near.app.controller.EntityController;
import think.near.app.exception.OperationException;

@Path("/assign")
public class AssignmentEndpoint {

	private static EntityController _entityController = EntityController.getInstance();

	@GET
	@Path("/list/class/{code}")
	@Produces("application/json")
	public List<Student> listByClass(@PathParam("code") String code) throws OperationException {
		return _entityController.listAssignmentsByClass(code);
	}

	@GET
	@Path("/list/student/{id}")
	@Produces("application/json")
	public List<Class> listByClass(@PathParam("id") int id) throws OperationException {
		return _entityController.listAssignmentsByStudent(id);
	}
	
	@PUT
	@Path("/{id}/{code}")
	@Produces("application/json")
	public SimpleResponse assignStudentToClass(@PathParam("id") int id, @PathParam("code") String code) throws OperationException {
		boolean success = _entityController.assignStudentToClass(id, code);
		if (success) {
			return new SimpleResponse("assigned", "Student " + id + " was assigned to class " + code);
		} else {
			return new SimpleResponse("unmodified", "Already assigned or one of the identifiers does not exist");
		}
	}
}
