package dev.jgregorio.handle.ticket.domain.services;

import dev.jgregorio.handle.ticket.application.ports.in.ParseTicketCase;
import dev.jgregorio.handle.ticket.application.ports.out.ParseTicketPort;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class ParseTicketService implements ParseTicketCase {
    private ParseTicketPort parseTicketPort;

    @Override
    public ParsedTicket parseTicket(LoadedTicket loadedTicket) throws IOException {
        return parseTicketPort.parseTicket(loadedTicket);
    }
}
