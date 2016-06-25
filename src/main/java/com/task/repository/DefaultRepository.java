package com.task.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.task.entity.Task;

public class DefaultRepository {

	@PersistenceContext
	private EntityManager manager;

	public Task save(Task task) {
		manager.persist(task);
		return task;
	}
	
	public void edit(Task task) {
		manager.merge(task);
	}

	public List<Task> listAll(EntityPath<?> qEntity) {
		return createQuery().from(qEntity).fetch();
	}

	public Task findById(EntityPath<?> entity, Predicate predicate) {
		return createQuery().from(entity).where(predicate).fetchOne();
	}
	
	public void delete(Task task) {
		manager.remove(task);
	}

	public JPAQuery<Task> createQuery() {
		return new JPAQuery<Task>(manager, HQLTemplates.DEFAULT);
	}

}
