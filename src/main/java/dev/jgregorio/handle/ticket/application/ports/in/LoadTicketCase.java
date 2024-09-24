package dev.jgregorio.handle.ticket.application.ports.in;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;

import java.io.IOException;

public interface LoadTicketCase {
    LoadedTicket loadTicket(Ticket ticket) throws IOException;
}
