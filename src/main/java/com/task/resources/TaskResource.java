package com.task.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@GET
	@Path("{id}")
	public Task getById(@PathParam("id") Long id) {
		return service.findById(id);
	}

	@GET
	@Path("all")
	public List<Task> getAll() {
		return service.selectAll();
	}

	@GET
	@Path("overdue")
	public List<Task> getOverdue() {
		return service.getOverdueTasks();
	}

	@PUT
	@Path("edit")
	public Task edit(Task task) throws TaskException {
		return service.edit(task);
	}

	@DELETE
	@Path("delete/{id}")
	public void delete(@PathParam("id") Long id) {
		service.delete(id);
	}

}
