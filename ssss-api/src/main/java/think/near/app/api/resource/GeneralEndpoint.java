package think.near.app.api.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import engine.model.entity.Student;
import think.near.app.controller.EntityController;
import think.near.app.exception.OperationException;

@Path("/")
public class GeneralEndpoint {

	private static EntityController _studentController = EntityController.getInstance();

	@GET
	@Produces("application/json")
	public List<Student> listAll(@QueryParam("q") String query) throws OperationException {
		if (query == null || query.isEmpty()) {
			return _studentController.getAllStudents();
		} else {
			return _studentController.findStudents(query.toLowerCase());
		}
	}

}
