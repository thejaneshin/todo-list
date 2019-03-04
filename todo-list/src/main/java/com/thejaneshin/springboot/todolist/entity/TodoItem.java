package com.thejaneshin.springboot.todolist.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="todo_item")
public class TodoItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	// Might have to change timezone
	@DateTimeFormat(pattern ="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name="deadline")
	private LocalDateTime deadline;
	
	@Column(name="status")
	private boolean status;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="todo_list_id")
	private TodoList list;
	
	public TodoItem() {
		
	}

	public TodoItem(String description, LocalDateTime deadline, boolean status, TodoList list) {
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TodoList getList() {
		return list;
	}

	public void setList(TodoList list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", description=" + description + ", deadline=" + deadline + ", status=" + status
				+ "]";
	}
}
