package dev.jgregorio.handle.ticket.application.ports.in;

import dev.jgregorio.handle.ticket.domain.model.Ticket;

public interface CreateTicketCase {
    Ticket createTicket(Ticket ticket);
}
