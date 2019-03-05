package com.thejaneshin.springboot.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaneshin.springboot.todolist.entity.TodoList;
import com.thejaneshin.springboot.todolist.repository.TodoListRepository;

@Service
public class TodoListServiceImpl implements TodoListService {
	private TodoListRepository todoListRepository;
	
	@Autowired
	public TodoListServiceImpl(TodoListRepository theTodoListRepository) {
		todoListRepository = theTodoListRepository;
	}
	
	@Override
	public List<TodoList> findAll() {
		return todoListRepository.findAll();
	}

	@Override
	public TodoList findById(int todoListId) {
		Optional<TodoList> result = todoListRepository.findById(todoListId);
		TodoList theTodoList = null;
		
		if (result.isPresent())
			theTodoList = result.get();
		else
			throw new RuntimeException("Did not find TodoList id - " + todoListId);
		return theTodoList;
	}

	@Override
	public void save(TodoList theTodoList) {
		todoListRepository.save(theTodoList);
	}

	@Override
	public void deleteById(int theId) {
		todoListRepository.deleteById(theId);
	}

}
