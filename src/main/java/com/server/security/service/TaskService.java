package com.server.security.service;

import com.server.security.Iservice.TaskIService;
import com.server.security.dto.TaskDto;
import com.server.security.entity.Task;
import com.server.security.entity.Ticket;
import com.server.security.exception.ResourceNotFoundExeption;
import com.server.security.mapper.TaskMapper;
import com.server.security.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskIService {
    private final TaskRepository taskRepository;
    private final TicketService ticketService;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper , TicketService ticketService) {
        this.taskRepository = taskRepository;
        this.ticketService = ticketService;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> taskMapper.toDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByTicket(Long ticketId) {
        List<Task> tasks = taskRepository.getTaskByTicketTicketId(ticketId);
        return tasks.stream()
                .map(task -> taskMapper.toDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = getTaskEntityById(taskId);
        return taskMapper.toDto(task);
    }
    public Task getTaskEntityById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundExeption("Task" ,"TaskId" , taskId.toString())
        );
        return task;
    }

    @Override
    public TaskDto createTask(Long ticketId, TaskDto taskDTO) {
        Ticket ticket = ticketService.getTicketEntityById(ticketId);
        Task newTask = taskMapper.toEntity(taskDTO);
        newTask.setTicket(ticket);
        Task createdTask = taskRepository.save(newTask);
        return taskMapper.toDto(createdTask);
    }

    @Override
    public TaskDto updateTask(Long taskId , TaskDto taskDTO) {
        Task task = getTaskEntityById(taskId);
        task.setName(taskDTO.getName());
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
