package com.task.operation.test;

import javax.persistence.EntityManager;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.task.operation.PersistObject;

public class PersistObjectTest {
	
	@InjectMocks
	private PersistObject persistObject;
	@Mock
	private EntityManager manager;
	

}
