package com.server.security.mapper;

import com.server.security.dto.TaskDto;
import com.server.security.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements EntityDTOMapper<Task, TaskDto>{
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public TaskDto toDto(Task entity) {
        return modelMapper.map(entity , TaskDto.class);
    }

    @Override
    public Task toEntity(TaskDto dto) {
        return modelMapper.map(dto , Task.class);
    }
}
