package dev.jgregorio.handle.ticket.application.ports.in;

import dev.jgregorio.handle.ticket.common.exception.CreateTicketCaseException;
import dev.jgregorio.handle.ticket.domain.Ticket;
import org.springframework.core.io.InputStreamSource;

/**
 * AS user
 * I WANT TO create ticket
 * SO I can persist ticket data
 */
public interface CreateTicketCase {
    Ticket createTicket(InputStreamSource ticketSource, String contentType) throws CreateTicketCaseException;
}
