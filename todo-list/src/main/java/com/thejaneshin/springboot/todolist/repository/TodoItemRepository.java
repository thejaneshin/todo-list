package com.thejaneshin.springboot.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thejaneshin.springboot.todolist.entity.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {
	
}
