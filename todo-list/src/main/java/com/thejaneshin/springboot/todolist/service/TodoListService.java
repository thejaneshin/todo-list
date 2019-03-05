package com.thejaneshin.springboot.todolist.service;

import java.util.List;

import com.thejaneshin.springboot.todolist.entity.TodoList;

public interface TodoListService {
	public List<TodoList> findAll();
	
	public TodoList findById(int todoListId);
	
	public void save(TodoList theTodoList);
	
	public void deleteById(int theId);
}
