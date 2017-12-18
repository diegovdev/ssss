package think.near.app.api.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import think.near.app.exception.OperationException;

@Provider
public class RestExceptionMapper implements ExceptionMapper<OperationException> {

	@Override
	public Response toResponse(OperationException exception) {
		return Response
				.status(404)
				.entity(exception.getMessage())
				.type("text/plain").build();
	}
}
