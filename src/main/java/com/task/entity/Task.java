package com.task.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	@NotNull(message = "Name can't be null")
	private String name;

	@NotNull(message = "Date start can't be null")
	private Date dateStart;

	@NotNull(message = "Date finish can't be null")
	private Date dateFinish;
	
	@Transient
	private boolean isOverdue;

}
