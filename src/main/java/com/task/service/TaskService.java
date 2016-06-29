package com.task.service;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.task.entity.Task;
import com.task.exception.TaskException;
import com.task.repository.DefaultRepository;

@Stateless
public class TaskService {

	@Inject
	private DefaultRepository repository;

	@Inject
	private Validator validator;

	public Task save(Task task) throws TaskException {
		validate(task);
		return repository.save(task);
	}

	private Task validate(Task task) throws TaskException {
		Set<ConstraintViolation<Task>> violationList = validator.validate(task);
		if (!violationList.isEmpty()) {
			throw new TaskException(buildExceptionMessage(violationList));
		}
		return task;
	}

	private String buildExceptionMessage(Set<ConstraintViolation<Task>> violationList) {
		String message = "";
		for (ConstraintViolation<Task> constraintViolation : violationList) {
			message += constraintViolation.getMessage() + "\n";
		}
		return message.trim();
	}

}
