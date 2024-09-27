package dev.jgregorio.handle.ticket.domain.services;

import dev.jgregorio.handle.ticket.application.ports.in.CreateTicketCase;
import dev.jgregorio.handle.ticket.application.ports.out.CreateTicketPort;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTicketService implements CreateTicketCase {

    private CreateTicketPort createTicketPort;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return createTicketPort.createTicket(ticket);
    }
}
