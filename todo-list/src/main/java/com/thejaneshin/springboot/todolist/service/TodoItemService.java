package com.thejaneshin.springboot.todolist.service;

import java.util.List;

import com.thejaneshin.springboot.todolist.entity.TodoItem;

public interface TodoItemService {
	public List<TodoItem> findAll();
	
	public TodoItem findById(int todoItemId);
	
	public void save(TodoItem theTodoItem);
	
	public void deleteById(int todoItemId);
}
