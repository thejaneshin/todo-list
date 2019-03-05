package com.thejaneshin.springboot.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaneshin.springboot.todolist.entity.TodoItem;
import com.thejaneshin.springboot.todolist.repository.TodoItemRepository;

@Service
public class TodoItemServiceImpl implements TodoItemService {
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	public TodoItemServiceImpl(TodoItemRepository theTodoItemRepository) {
		todoItemRepository = theTodoItemRepository;
	}
	
	@Override
	public List<TodoItem> findAll() {
		return todoItemRepository.findAll();
	}

	@Override
	public TodoItem findById(int todoItemId) {
		Optional<TodoItem> result = todoItemRepository.findById(todoItemId);
		TodoItem theTodoItem = null;
		
		if (result.isPresent()) {
			theTodoItem = result.get();
		}
		else {
			throw new RuntimeException("Did not find TodoItem id - " + todoItemId);
		}
		return theTodoItem;
	}

	@Override
	public TodoItem findByListId(int todoListId) {
		return todoItemRepository.findByListId(todoListId);
	}

	@Override
	public TodoItem findbyIdAndListId(int todoItemId, int todoListId) {
		return todoItemRepository.findbyIdAndListId(todoItemId, todoListId);
	}
	
	@Override
	public void save(TodoItem theTodoItem) {
		todoItemRepository.save(theTodoItem);
	}

	@Override
	public void deleteById(int theId) {
		todoItemRepository.deleteById(theId);
	}

}
