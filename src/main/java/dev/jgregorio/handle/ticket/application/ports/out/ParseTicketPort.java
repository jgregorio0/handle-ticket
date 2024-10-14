package dev.jgregorio.handle.ticket.application.ports.out;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;

import java.io.IOException;

public interface ParseTicketPort {
    ParsedTicket parseTicket(LoadedTicket loadedTicket) throws IOException;
}
