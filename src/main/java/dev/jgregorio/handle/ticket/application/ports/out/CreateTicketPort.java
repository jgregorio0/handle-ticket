package dev.jgregorio.handle.ticket.application.ports.out;

import dev.jgregorio.handle.ticket.domain.Ticket;

public interface CreateTicketPort {
    Ticket createTicket(Ticket ticket);
}
