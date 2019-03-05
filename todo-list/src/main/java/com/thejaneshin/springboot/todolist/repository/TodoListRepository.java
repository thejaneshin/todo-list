package com.thejaneshin.springboot.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thejaneshin.springboot.todolist.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {

}
