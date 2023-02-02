package com.sbmongo.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Person {
	
	private String id;
	
	private String name;
	
	private int age;
	
	public Person(String name, int age){
		this.name= name;
		this.age = age;
	}

}
