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

import io.swagger.annotations.ApiOperation;

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
	
	@ApiOperation(value="View all the todo lists")
	@GetMapping(value="/lists", produces="application/json")
	public List<TodoList> findAll() {
		return todoListService.findAll();
	}
	
	@ApiOperation(value="Search for a todo list, given a todoListId")
	@GetMapping(value="/lists/{todoListId}", produces="application/json")
	public TodoList getTodoList(@PathVariable int todoListId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		return theTodoList;
	}
	
	@ApiOperation(value="View all the todo items, given a todoListId")
	@GetMapping(value="/lists/{todoListId}/items", produces="application/json")
	public List<TodoItem> findAllTodoItemsById(@PathVariable int todoListId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		return theTodoList.getItems();
	}
	
	@ApiOperation(value="Search for a todo item, given both todoListId and todoItemId")
	@GetMapping(value="/lists/{todoListId}/items/{todoItemId}", produces="application/json")
	public TodoItem getTodoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		TodoItem theTodoItem = todoItemService.findById(todoItemId);
		if (theTodoItem == null)
			throw new RuntimeException("TodoItem id not found - " + todoItemId);
		return theTodoItem;
	}
	
	@ApiOperation(value="Add a todo list")
	@PostMapping(value="/lists", produces="application/json")
	public TodoList addTodoList(@RequestBody TodoList theTodoList) {
		// Just in case user passes an id in JSON
		// This is to force a save of new item, instead of update
		theTodoList.setId(0);
		todoListService.save(theTodoList);
		return theTodoList;
	}
	
	@ApiOperation(value="Add a todo item, given a todoListId")
	@PostMapping(value="/lists/{todoListId}/items", produces="application/json")
	public TodoItem addTodoItem(@PathVariable int todoListId, @RequestBody TodoItem theTodoItem) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		theTodoItem.setStatus(false);
		theTodoItem.setList(theTodoList);
		todoItemService.save(theTodoItem);
		return theTodoItem;
	}
	
	@ApiOperation(value="Update a todo list")
	@PutMapping(value="/lists", produces="application/json")
	public TodoList updateTodoList(@RequestBody TodoList theTodoList) {
		todoListService.save(theTodoList);
		return theTodoList;
	}
	
	@ApiOperation(value="Update a todo item, given a todoListId")
	@PutMapping(value="/lists/{todoListId}/items", produces="application/json")
	public TodoItem updateTodoItem(@PathVariable int todoListId, @RequestBody TodoItem theTodoItem) {
		TodoList theTodoList = todoListService.findById(todoListId);
		if (theTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		theTodoItem.setList(theTodoList);
		todoItemService.save(theTodoItem);
		return theTodoItem;
	}
	
	@ApiOperation(value="Delete a todo list, given a todoListId")
	@DeleteMapping(value="/lists/{todoListId}", produces="application/json")
	public String deleteTodoList(@PathVariable int todoListId) {
		TodoList tempTodoList = todoListService.findById(todoListId);
		if (tempTodoList == null)
			throw new RuntimeException("TodoList id not found - " + todoListId);
		todoListService.deleteById(todoListId);
		return "Deleted TodoList id - " + todoListId;
	}
	
	@ApiOperation(value="Delete todo item, given both todoListId and todoItemId")
	@DeleteMapping(value="/lists/{todoListId}/items/{todoItemId}", produces="application/json")
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
