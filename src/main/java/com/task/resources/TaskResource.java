package com.task.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.task.entity.Task;
import com.task.exception.TaskException;
import com.task.service.TaskService;

@Path("task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

	@Inject
	private TaskService service;

	@POST
	@Path("new")
	public Task create(Task task) throws TaskException {
		return service.save(task);
	}

}
