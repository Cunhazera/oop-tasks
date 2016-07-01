package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.LocalDate;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.task.entity.QTask;
import com.task.entity.Task;

public class DefaultRepository {

	@PersistenceContext
	private EntityManager manager;

	private QTask qTask = QTask.task;

	public Task save(Task task) {
		manager.persist(task);
		return task;
	}

	public Task edit(Task task) {
		manager.merge(task);
		return task;
	}

	public List<Task> listAll() {
		return createQuery().from(qTask).fetch();
	}

	public Task findById(Long id) {
		return createQuery().from(qTask).where(qTask.id.eq(id)).fetchOne();
	}

	public List<Task> getOverdue() {
		return createQuery().from(qTask)
				.where(qTask.done.isFalse().and(qTask.dateFinish.before(new LocalDate().toDate()))).fetch();
	}

	public void delete(Long id) {
		new JPADeleteClause(manager, qTask).where(qTask.id.eq(id));
	}

	public JPAQuery<Task> createQuery() {
		return new JPAQuery<Task>(manager, HQLTemplates.DEFAULT);
	}

}
