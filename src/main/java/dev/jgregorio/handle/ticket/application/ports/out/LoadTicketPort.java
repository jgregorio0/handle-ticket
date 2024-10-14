package dev.jgregorio.handle.ticket.application.ports.out;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.Ticket;

import java.io.IOException;

public interface LoadTicketPort {
    LoadedTicket loadTicket(Ticket ticket) throws IOException;
}
