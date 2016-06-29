package com.task.service.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.validation.Validator;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.task.entity.Task;
import com.task.exception.TaskException;
import com.task.repository.DefaultRepository;
import com.task.service.TaskService;

public class TaskServiceTest {

	@InjectMocks
	private TaskService service;
	@Mock
	private DefaultRepository repository;
	@Mock
	private Validator validator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() throws TaskException {
		Task task = Task.builder().id(1L).name("Name").dateFinish(new LocalDate().toDate()).done(true).build();
		service.save(task);
		Mockito.verify(repository, Mockito.times(1)).save(task);
		assertThat(task, Matchers.notNullValue());
		assertThat(task.getDateFinish(), equalTo(new LocalDate().toDate()));
		assertThat(task.getName(), equalTo("Name"));
		assertThat(task.isDone(), equalTo(true));
	}

	@Test
	public void testSaveError() throws TaskException {
		Task task = Task.builder().id(1L).name(null).dateFinish(new LocalDate().toDate()).done(true).build();
		service.save(task);
	}

}
