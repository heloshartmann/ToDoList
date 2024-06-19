package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
