package com.server.security.repository;

import com.server.security.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getTaskByTicketTicketId(Long ticketId);
}
