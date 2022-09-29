package com.escuelaing.ieti.springboot.controller;

import com.escuelaing.ieti.springboot.dto.TaskDto;
import com.escuelaing.ieti.springboot.entities.Task;
import com.escuelaing.ieti.springboot.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v2/tasks")
public class TasksController {

    @Autowired
    TaskService taskService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/hello")
    public String sayHello(){
        //System.out.println("Print: Hello!!!");
        return "Hello!!!";
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        try {
            List<Task> tasks = taskService.getAll();
            List<TaskDto> data = new ArrayList<TaskDto>();
            if (!tasks.isEmpty()) {
                for (Task t : tasks) {
                    data.add(modelMapper.map(t, TaskDto.class));
                }
            }
            return new ResponseEntity<List<TaskDto>>(data, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable String id) {
        try {
            Task taskTemp = taskService.findById(id);
            if (taskTemp != null) {
                return new ResponseEntity<TaskDto>(modelMapper.map(taskTemp, TaskDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {
        try {
            Task taskTemp = taskService.create(modelMapper.map(taskDto, Task.class));
            if (taskTemp != null) {
                return new ResponseEntity<TaskDto>(modelMapper.map(taskTemp, TaskDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch(Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto taskDto, @PathVariable String id){
        try {
            Task taskTemp = taskService.update(modelMapper.map(taskDto, Task.class), id);
            if (taskTemp != null) {
                return new ResponseEntity<TaskDto>(modelMapper.map(taskTemp, TaskDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete (@PathVariable String id) {
        try {
            boolean deleted = taskService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("\n");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

