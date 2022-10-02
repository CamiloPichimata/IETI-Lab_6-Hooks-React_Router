package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.Task;
import com.escuelaing.ieti.springboot.repository.TaskRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceMongoDB implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceMongoDB(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        try {
            taskRepository.insert(task);
            Optional<Task> taskTemp = taskRepository.findById(task.getId());
            return taskTemp.orElse(null);
        } catch (DuplicateKeyException e) {
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    @Override
    public Task findById(String id) {
        try {
            Optional<Task> taskTemp = taskRepository.findById(id);
            return taskTemp.orElse(null);
        } catch (NoSuchElementException e) {
            System.out.println("The specified id is not registered");
            return null;
        }
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Task update(Task task, String taskId) {
        if (taskRepository.existsById(taskId)) {
            return taskRepository.save(task);
        } else {
            System.out.println("The specified id is not registered");
            return null;
        }
    }
}
