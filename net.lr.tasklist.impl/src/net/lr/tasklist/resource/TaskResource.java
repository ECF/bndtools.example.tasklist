package net.lr.tasklist.resource;

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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import net.lr.tasklist.model.Task;
import net.lr.tasklist.model.TaskResourceService;
import net.lr.tasklist.model.TaskService;

@Component(service = TaskResourceService.class, 
property = { "service.exported.interfaces=*", 
			 "service.exported.intents=jaxrs",
			 "ecf.jaxrs.jersey.server.pathPrefix=/rsvcs" 
})
@Path("tasks")
public class TaskResource implements TaskResourceService {
	
	@Reference
	TaskService taskService;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("id") Integer id) {
		return taskService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task addTask(Task task) {
		task.setId(taskService.getAll().size() + 1);
		taskService.addOrUpdate(task);
		return task;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Task> getTasks() {
		return taskService.getAll();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void updateTask(@PathParam("id") Integer id, Task task) {
		if (!task.getId().equals(id)) {
			throw new IllegalStateException("Id from path and content must be the same");
		}
		taskService.addOrUpdate(task);
	}

	@DELETE
	@Path("{id}")
	public void deleteTask(@PathParam("id") Integer id) {
		taskService.delete(id);
	}

}