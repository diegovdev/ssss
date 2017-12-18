package think.near.app.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import engine.model.Student;
import think.near.app.controller.StudentController;

@Path("/")
public class GeneralResource {

	private static StudentController _studentController = StudentController.getInstance();

	@GET
	@Produces("application/json")
	public List<Student> listAll(@QueryParam("q") String query) {
		if (query == null || query.isEmpty()) {
			return _studentController.getAll();
		} else {
			return _studentController.findStudents(query.toLowerCase());
		}
	}

}
