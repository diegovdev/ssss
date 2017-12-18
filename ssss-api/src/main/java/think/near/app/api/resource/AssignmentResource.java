package think.near.app.api.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import engine.model.entity.Student;
import think.near.app.api.util.SimpleResponse;
import think.near.app.controller.EntityController;
import think.near.app.exception.OperationException;

@Path("/assignment")
public class AssignmentResource {

	private static EntityController _entityController = EntityController.getInstance();

	@GET
	@Produces("application/json")
	public List<Student> listAll(@QueryParam("q") String query) throws OperationException {
		if (query == null || query.isEmpty()) {
			return _entityController.getAllStudents();
		} else {
			return _entityController.listAssignmentsByClass("");
		}
	}

	@PUT
	@Path("/{id}/{code}")
	@Produces("application/json")
	public SimpleResponse assignStudentToClass(@PathParam("id") int id, @PathParam("id") String code) throws OperationException {
		boolean success = _entityController.assignStudentToClass(id, code);
		if (success) {
			return new SimpleResponse("assigned", "Student " + id + " was assigned to class " + code);
		} else {
			return new SimpleResponse("unmodified", "Already assigned or one of the identifiers does not exist");
		}
	}
}
