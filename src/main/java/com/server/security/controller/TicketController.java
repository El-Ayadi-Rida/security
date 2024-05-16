package com.server.security.controller;

import com.server.security.dto.TicketDto;
import com.server.security.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<TicketDto> creatTicket(
            @Valid @RequestBody TicketDto ticketDto
                        ){
        TicketDto createdTicket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(createdTicket , HttpStatus.CREATED);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticket_id){
        TicketDto ticketDto = ticketService.getTicketById(ticket_id);
        return new ResponseEntity<>(ticketDto , HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{ticket_id}")
    public ResponseEntity<TicketDto> updateTicket(
            @Valid @RequestBody TicketDto ticketDto,
            @PathVariable Long ticket_id
                            ){
        TicketDto updatedTicket = ticketService.updateTicket(ticket_id , ticketDto);
        return new ResponseEntity<>(updatedTicket , HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<TicketDto>> getAllTicket(){
        List<TicketDto> list = ticketService.getAllTickets();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ticket_id}")
    public ResponseEntity<String> deleteTicket(
            @PathVariable("ticket_id") Long ticket_id){
        ticketService.deleteTicket(ticket_id);
        return new ResponseEntity<>("Ticket deleted succsfully" , HttpStatus.OK);
    }
}
