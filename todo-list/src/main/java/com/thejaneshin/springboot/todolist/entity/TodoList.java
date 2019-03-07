package com.thejaneshin.springboot.todolist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="todo_list")
public class TodoList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated todo list ID")
	@Column(name="id")
	private int id;
	
	@NotNull
	@ApiModelProperty(notes = "The title of the todo list")
	@Column(name="title")
	private String title;
	
	@JsonManagedReference
	@ApiModelProperty(notes = "The list of todo items")
	@OneToMany(fetch=FetchType.LAZY, 
			   mappedBy="list",
			   cascade=CascadeType.ALL)
	private List<TodoItem> items;
	
	public TodoList() {
		
	}

	public TodoList(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", title=" + title + "]";
	}
	
}
