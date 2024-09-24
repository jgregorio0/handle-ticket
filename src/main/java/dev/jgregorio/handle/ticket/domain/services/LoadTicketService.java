package dev.jgregorio.handle.ticket.domain.services;

import dev.jgregorio.handle.ticket.application.ports.in.LoadTicketCase;
import dev.jgregorio.handle.ticket.application.ports.out.LoadTicketPort;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadTicketService implements LoadTicketCase {

    private LoadTicketPort loadTicketPort;

    @Override
    public LoadedTicket loadTicket(Ticket ticket) throws IOException {
        return loadTicketPort.loadTicket(ticket);
    }
}
