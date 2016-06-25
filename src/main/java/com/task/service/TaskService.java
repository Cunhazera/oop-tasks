package com.task.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.task.entity.Task;
import com.task.repository.DefaultRepository;

@Stateless
public class TaskService {

	@Inject
	private DefaultRepository repository;

	public Task save(Task task) {
		return repository.save(task);
	}

}
