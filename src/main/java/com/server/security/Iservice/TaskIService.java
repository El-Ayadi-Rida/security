package com.server.security.Iservice;

import com.server.security.dto.TaskDto;
import com.server.security.dto.TicketDto;

import java.util.List;

public interface TaskIService {
    List<TaskDto> getAllTasks();
    List<TaskDto> getTasksByTicket(Long ticketId);

    TaskDto getTaskById(Long taskId);

    TaskDto createTask(Long ticketId , TaskDto taskDTO);
    TaskDto updateTask(Long taskId ,TaskDto taskDTO);

    void deleteTask(Long taskId);
}
