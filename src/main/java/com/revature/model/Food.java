package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * In order for this to be a persistent class we need a no-args
 * constructor and a primary key
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="foods")
public class Food { // the table will be named "foods"

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private @NonNull String dishName;
	
	// you can't put @NonNull on a primitive because null is never possible
	// 0 is the default value for the int primitive value
	private int calories;
	
}
