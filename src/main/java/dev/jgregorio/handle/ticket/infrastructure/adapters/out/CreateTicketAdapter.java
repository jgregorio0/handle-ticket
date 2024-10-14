package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.application.mappers.TicketMapper;
import dev.jgregorio.handle.ticket.application.ports.out.CreateTicketPort;
import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.database.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class CreateTicketAdapter implements CreateTicketPort {

    TicketRepository ticketRepository;
    TicketMapper ticketMapper;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketMapper.toModel(
                ticketRepository.save(
                        ticketMapper.toEntity(ticket)));
    }
}
