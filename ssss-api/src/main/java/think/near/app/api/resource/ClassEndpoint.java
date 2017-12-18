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

import engine.model.entity.Class;
import think.near.app.api.util.SimpleResponse;
import think.near.app.controller.EntityController;
import think.near.app.exception.OperationException;


@Path("/class/")
public class ClassEndpoint {

	private EntityController _entityController = EntityController.getInstance();

	@GET
	@Produces("application/json")
	public List<Class> listClasses() throws OperationException {
		return _entityController.getAllClasses();
	}

	@GET
	@Path("/{code}")
	@Produces("application/json")
	public Class getClass(@PathParam("code") String classCode) throws OperationException {
		return _entityController.getClass(classCode);
	}

	@POST
	@Path("/")
	@Produces("application/json")
    @Consumes("application/json")
	public Class createClass(Class c) throws OperationException {
		_entityController.createClass(c);
		return c;
	}

	@POST
	@Path("/{code}")
	@Produces("application/json")
    @Consumes("application/json")
	public SimpleResponse updateClass(@PathParam("code") String code, Class c) throws OperationException {
		boolean success = _entityController.updateClass(code, c);
		if (success) {
			return new SimpleResponse("updated", "Updated class with code " + code);
		} else {
			return new SimpleResponse("unmodified", "No class found with code " + code);
		}
	}

	@DELETE
	@Path("/{code}")
	@Produces("application/json")
	public SimpleResponse deleteClass(@PathParam("code") String code) throws OperationException {
		boolean success = _entityController.deleteClass(code);
		if (success) {
			return new SimpleResponse("deleted", "Deleted class with code " + code);
		} else {
			return new SimpleResponse("unmodified", "No class found with code " + code);
		}
	}

}
