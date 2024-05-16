package com.server.security.service;

import com.server.security.Iservice.TicketIService;
import com.server.security.dto.TicketDto;
import com.server.security.entity.Ticket;
import com.server.security.exception.ResourceNotFoundExeption;
import com.server.security.mapper.TicketMapper;
import com.server.security.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements TicketIService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> ticketMapper.toDto(ticket))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(Long ticketId) {
        Ticket ticketById = getTicketEntityById(ticketId);
        return ticketMapper.toDto(ticketById);
    }

    public Ticket getTicketEntityById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                ()-> new ResourceNotFoundExeption("Ticket" , "TicketId" , ticketId.toString())
        );
        return ticket;
    }
    @Override
    public TicketDto createTicket(TicketDto ticketDTO) {
        Ticket newTicket = ticketMapper.toEntity(ticketDTO);
        Ticket createdTicket = ticketRepository.save(newTicket);
        return ticketMapper.toDto(createdTicket);
    }

    @Override
    public TicketDto updateTicket(Long ticketId ,TicketDto ticketDTO) {
        Ticket ticket = getTicketEntityById(ticketId);
        ticket.setName(ticketDTO.getName());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
