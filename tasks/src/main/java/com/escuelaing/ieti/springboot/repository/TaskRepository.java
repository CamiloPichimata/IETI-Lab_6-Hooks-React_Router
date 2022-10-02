package com.escuelaing.ieti.springboot.repository;

import com.escuelaing.ieti.springboot.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
