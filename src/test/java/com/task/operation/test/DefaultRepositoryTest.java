package com.task.operation.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.task.entity.QTask;
import com.task.entity.Task;
import com.task.repository.DefaultRepository;

public class DefaultRepositoryTest {

	@Spy
	@InjectMocks
	private DefaultRepository repository;
	@Mock
	private EntityManager manager;
	@Mock
	private JPAQuery<Task> jpaQuery;

	private QTask qTask = QTask.task;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(repository.createQuery()).thenReturn(jpaQuery);
		Mockito.when(jpaQuery.from(qTask)).thenReturn(jpaQuery);
	}

	@Test
	public void assertDetails() {
		Task task = buildTask(1L, "One task", new LocalDate().toDate(), true);
		assertThat(task.getName(), equalTo("One task"));
		assertThat(task.getDateFinish(), equalTo(new LocalDate().toDate()));
		assertThat(task.isDone(), equalTo(true));
	}

	@Test
	public void save() {
		Task task = buildTask(1L, "Task", new Date(), true);
		repository.save(task);
		Mockito.verify(manager).persist(task);
		Mockito.verify(repository).save(task);
	}

	@Test
	public void edit() {
		Task task = buildTask(1L, "Name", new LocalDate().toDate(), true);
		repository.edit(task);
		Mockito.verify(manager, Mockito.never()).persist(task);
		verify(manager).merge(task);
	}

	@Test
	public void delete() {
		Task task = buildTask(1L, "Name", new LocalDate().toDate(), true);
		repository.delete(task);
		verify(repository, Mockito.times(1)).delete(task);
	}

	@Test
	public void listAll() {
		List<Task> list = Arrays.asList(buildTask(1L, "Task1", new LocalDate().toDate(), true),
				buildTask(2L, "Task2", new LocalDate().toDate(), false));
		Mockito.when(jpaQuery.fetch()).thenReturn(list);
		List<Task> returnedList = repository.listAll(qTask);
		assertThat(returnedList.size(), equalTo(2));
		assertThat(returnedList.get(0).getId(), equalTo(1L));
		assertThat(returnedList.get(1).getId(), equalTo(2L));
	}

	@Test
	public void findById() {
		BooleanExpression predicate = qTask.id.eq(1L);
		Task task = buildTask(1L, "FindTask", new Date(), false);
		when(jpaQuery.from(qTask).where(predicate)).thenReturn(jpaQuery);
		when(jpaQuery.from(qTask).where(predicate).fetchOne()).thenReturn(task);
		Task returnedTask = repository.findById(qTask, predicate);
		assertThat(returnedTask.getName(), equalTo("FindTask"));
	}

	private Task buildTask(long id, String name, Date dateFinish, boolean done) {
		return Task.builder().id(id).name(name).dateFinish(dateFinish).done(done).build();
	}

}
