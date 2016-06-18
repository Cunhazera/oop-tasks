package com.task.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("status")
public class StatusResource {

	@GET
	public String status() {
		return "OK";
	}

}
