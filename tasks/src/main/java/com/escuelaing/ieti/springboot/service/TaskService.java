package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.Task;

import java.util.List;

public interface TaskService {

    Task create(Task task );

    Task findById( String id );

    List<Task> getAll();

    boolean deleteById( String id );

    Task update( Task task, String id );

}
