package dev.jgregorio.handle.ticket.infrastructure.database;

import dev.jgregorio.handle.ticket.application.mappers.TicketMapper;
import dev.jgregorio.handle.ticket.application.ports.out.CreateTicketPort;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
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
