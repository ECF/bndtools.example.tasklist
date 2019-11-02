package net.lr.tasklist.model;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
public interface TaskResourceService {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("id") Integer id);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task addTask(Task task);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Task> getTasks();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void updateTask(@PathParam("id") Integer id, Task task);

	@DELETE
	@Path("{id}")
	public void deleteTask(@PathParam("id") Integer id);
}
