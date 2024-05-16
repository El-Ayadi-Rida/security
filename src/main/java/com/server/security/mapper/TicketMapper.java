package com.server.security.mapper;

import com.server.security.dto.TicketDto;
import com.server.security.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements EntityDTOMapper<Ticket, TicketDto> {
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public TicketDto toDto(Ticket entity) {
        return modelMapper.map(entity , TicketDto.class);
    }

    @Override
    public Ticket toEntity(TicketDto dto) {
        return modelMapper.map(dto , Ticket.class);
    }
}
