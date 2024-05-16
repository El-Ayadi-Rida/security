package com.server.security.controller;

import com.server.security.dto.TaskDto;
import com.server.security.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{ticket_id}")
    public ResponseEntity<TaskDto> creatTask(
            @Valid @RequestBody TaskDto taskDto,
            @PathVariable Long ticket_id
    ){
        TaskDto createdTask = taskService.createTask(ticket_id ,taskDto);
        return new ResponseEntity<>(createdTask , HttpStatus.CREATED);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long task_id){
        TaskDto taskDto = taskService.getTaskById(task_id);
        return new ResponseEntity<>(taskDto , HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{task_id}")
    public ResponseEntity<TaskDto> updateTask(
            @Valid @RequestBody TaskDto taskDto,
            @PathVariable Long task_id
    ){
        TaskDto updatedTask = taskService.updateTask(task_id , taskDto);
        return new ResponseEntity<>(updatedTask , HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTask(){
        List<TaskDto> list = taskService.getAllTasks();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable("task_id") Long task_id){
        taskService.deleteTask(task_id);
        return new ResponseEntity<>("Task deleted succsfully" , HttpStatus.OK);
    }
}
