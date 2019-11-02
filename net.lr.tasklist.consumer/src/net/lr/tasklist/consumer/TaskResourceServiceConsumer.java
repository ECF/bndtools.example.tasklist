package net.lr.tasklist.consumer;

import java.util.Date;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import net.lr.tasklist.model.Task;
import net.lr.tasklist.model.TaskResourceService;

@Component(immediate = true, service = TaskResourceServiceConsumer.class)
public class TaskResourceServiceConsumer {

	@Reference(target = "(service.imported=*)")
	private TaskResourceService svc;

	@Activate
	void activate() {
		System.out.println("Getting all tasks");
		svc.getTasks().forEach(t -> System.out.println("Task=" + t));
		System.out.println("Getting first task");
		System.out.println("Task 1=" + svc.getTask(1));
		Task t = new Task(0, "Greatest Task", "The greatest task of all time");
		t.setDueDate(new Date());
		t.setFinished(false);
		t = svc.addTask(t);
		System.out.println("Added task=" + t);
		t.setFinished(true);
		svc.updateTask(t.getId(), t);
		System.out.println("Updated task=" + t);
		svc.deleteTask(t.getId());
		System.out.println("Deleted task=" + t);
	}
}
