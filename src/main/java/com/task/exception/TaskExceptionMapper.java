package com.task.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TaskExceptionMapper implements ExceptionMapper<TaskException> {

	@Override
	public Response toResponse(TaskException exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

}
