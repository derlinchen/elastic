package com.elastic.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "library",type = "book")
public class Book {
	
	@Id
	@Field(index=true, store = true, type = FieldType.Integer)
	private int id;
	
	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_max_word", type = FieldType.Text)
	private String name;
	
	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_max_word", type = FieldType.Text)
	private String person;
	
	public Book() {
	}
	
	public Book(int id, String name, String person) {
		this.id = id;
		this.name = name;
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
}
