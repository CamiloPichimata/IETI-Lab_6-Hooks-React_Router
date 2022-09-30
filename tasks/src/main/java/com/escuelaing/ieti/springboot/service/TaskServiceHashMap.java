package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskServiceHashMap implements TaskService {
    private HashMap<String, Task> tasks = new HashMap<String, Task>();

    public TaskServiceHashMap () {
        Task task1 = new Task("10", "Tarea 1", "Realizar una investigación acerca de los términos seleccionados", "TODO", "Pablo López", "29/09/2022", "28/09/2022");
        Task task2 = new Task("20", "Tarea 2", "Realizar un informe sobre los términos especificados", "TODO", "Pablo López", "30/09/2022", "25/09/2022");
        tasks.put(task1.getId(), task1);
        tasks.put(task2.getId(), task2);
    }

    @Override
    public Task create(Task task) {
        if (tasks.containsKey(task.getId())) {
            return null;
        } else {
            tasks.put(task.getId(), task);
            return tasks.get(task.getId());
        }
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> getAll() {
        Collection<Task> values = tasks.values();
        return new ArrayList<Task>(values);
    }

    @Override
    public boolean deleteById(String id) {
        Task taskTemp = tasks.remove(id);
        return taskTemp != null;
    }

    @Override
    public Task update(Task task, String id) {
        if (tasks.containsKey(id)) {
            tasks.replace(id, task);
        }
        return tasks.get(id);
    }
}
