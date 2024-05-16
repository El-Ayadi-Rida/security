package com.server.security.Iservice;

import com.server.security.dto.TicketDto;

import java.util.List;

public interface TicketIService {
    List<TicketDto> getAllTickets();

    TicketDto getTicketById(Long ticketId);

    TicketDto createTicket(TicketDto ticketDTO);
    TicketDto updateTicket(Long ticketId ,TicketDto ticketDTO);

    void deleteTicket(Long ticketId);
}
