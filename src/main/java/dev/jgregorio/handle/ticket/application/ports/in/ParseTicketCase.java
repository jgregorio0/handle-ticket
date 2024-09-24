package dev.jgregorio.handle.ticket.application.ports.in;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;

import java.io.IOException;

public interface ParseTicketCase {
    ParsedTicket parseTicket(LoadedTicket loadedTicket) throws IOException;
}
