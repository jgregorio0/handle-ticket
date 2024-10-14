package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.application.mappers.TicketMapper;
import dev.jgregorio.handle.ticket.application.ports.out.FindTicketPort;
import dev.jgregorio.handle.ticket.common.exception.TicketNotFoundException;
import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.database.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FindTicketAdapter implements FindTicketPort {

    TicketRepository ticketRepository;
    TicketMapper ticketMapper;

    @Override
    public Ticket findTicket(Long id) throws TicketNotFoundException {
        return ticketMapper.toModel(
                ticketRepository.findById(id)
                        .orElseThrow(() -> new TicketNotFoundException(
                                String.format("Ticket %d could not be found", id))));
    }
}
