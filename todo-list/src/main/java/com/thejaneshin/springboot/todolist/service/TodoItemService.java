package com.thejaneshin.springboot.todolist.service;

import java.util.List;
import java.util.Optional;

import com.thejaneshin.springboot.todolist.entity.TodoItem;

public interface TodoItemService {
	public List<TodoItem> findAll();
	
	public TodoItem findById(int todoItemId);
	
	public TodoItem findByListId(int todoListId);
	
	public TodoItem findbyIdAndListId(int todoItemId, int todoListId);
	
	public void save(TodoItem theTodoItem);
	
	public void deleteById(int theId);
}
