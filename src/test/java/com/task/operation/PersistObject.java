package com.task.operation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistObject {
	
	@PersistenceContext
	private EntityManager manager;

}
