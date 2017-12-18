package think.near.app.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import engine.model.Student;
import think.near.app.controller.StudentController;

@Path("/student/")
public class StudentResource {

    @GET
    @Produces("application/json")
    public List<Student> listStudents(@QueryParam("q") String query) {
        if (query == null || query.isEmpty()) {
            return StudentController.getAll();
        } else {
            return StudentController.findStudents(query.toLowerCase());
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Student getStudent(@PathParam("id") int studentId) {
        return StudentController.getStudent(studentId);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("text/plain")
    public String putStudent(@PathParam("id") int studentId, Student c) {
    	//Response r = Response.status(200).entity("yeah").build();
        return "yeah " + studentId;
    }

}
