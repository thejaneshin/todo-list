package com.thejaneshin.springboot.todolist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejaneshin.springboot.todolist.entity.TodoItem;
import com.thejaneshin.springboot.todolist.entity.TodoList;
import com.thejaneshin.springboot.todolist.service.TodoItemService;
import com.thejaneshin.springboot.todolist.service.TodoListService;

@RestController
@RequestMapping("/api")
public class TodoRestController {
	private TodoListService todoListService;
	private TodoItemService todoItemService;
	
	@Autowired
	public TodoRestController(TodoListService theTodoListService, TodoItemService theTodoItemService) {
		todoListService = theTodoListService;
		todoItemService = theTodoItemService;
	}
	
	@GetMapping("/lists")
	public List<TodoList> findAll() {
		return todoListService.findAll();
	}
	
	@GetMapping("/lists/{todoListId}")
	public TodoList getTodoList(@PathVariable int todoListId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		return theTodoList;
	}
	
	@GetMapping("/lists/{todoListId}/items")
	public List<TodoItem> findAllPostsById(@PathVariable int todoListId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		return theTodoList.getItems();
	}
	
	@GetMapping("/lists/{todoListId}/items/{todoItemId}")
	public TodoItem getTodoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		TodoItem theTodoItem = todoItemService.findById(todoItemId);
		if (theTodoItem == null)
			throw new RuntimeException("TodoItem id not found - " + todoItemId);
		return theTodoItem;
	}
	
	@PostMapping("/lists")
	public TodoList addTodoList(@RequestBody TodoList theTodoList) {
		// Just in case user passes an id in JSON
		// This is to force a save of new item, instead of update
		theTodoList.setId(0);
		todoListService.save(theTodoList);
		return theTodoList;
	}
	
	@PostMapping("/lists/{todoListId}/items")
	public TodoItem addTodoItem(@PathVariable int todoListId, @RequestBody TodoItem theTodoItem) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		theTodoItem.setStatus(false);
		theTodoItem.setList(theTodoList);
		todoItemService.save(theTodoItem);
		return theTodoItem;
	}
	
	@PutMapping("/lists")
	public TodoList updateTodoList(@RequestBody TodoList theTodoList) {
		todoListService.save(theTodoList);
		return theTodoList;
	}
	
	@PutMapping("/lists/{todoListId}/items")
	public TodoItem updateTodoItem(@PathVariable int todoListId, @RequestBody TodoItem theTodoItem) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		theTodoItem.setList(theTodoList);
		todoItemService.save(theTodoItem);
		return theTodoItem;
	}
	
	@DeleteMapping("/lists/{todoListId}")
	public String deleteTodoList(@PathVariable int todoListId) {
		TodoList tempTodoList = todoListService.findById(todoListId);
		if (tempTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		todoListService.deleteById(todoListId);
		return "Deleted TodoList id - " + todoListId;
	}
	
	@DeleteMapping("/lists/{todoListId}/items/{todoItemId}")
	public String deleteTodoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
		TodoList tempTodoList = todoListService.findById(todoListId);
		if (tempTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		TodoItem theTodoItem = todoItemService.findById(todoItemId);
		if (theTodoItem == null)
			throw new RuntimeException("TodoItem id not found - " + todoItemId);
		todoItemService.deleteById(todoItemId);
		return "Deleted TodoItem id - " + todoItemId;
	}
}
