package dev.jgregorio.handle.ticket.application.ports.out;

import dev.jgregorio.handle.ticket.common.exception.TicketNotFoundException;
import dev.jgregorio.handle.ticket.domain.Ticket;

public interface FindTicketPort {

    Ticket findTicket(Long id) throws TicketNotFoundException;
}
