package dev.jgregorio.handle.ticket.domain.services;

import dev.jgregorio.handle.ticket.application.ports.in.CreateImageCase;
import dev.jgregorio.handle.ticket.application.ports.in.CreateTicketCase;
import dev.jgregorio.handle.ticket.application.ports.out.CreateImagePort;
import dev.jgregorio.handle.ticket.application.ports.out.CreateTicketPort;
import dev.jgregorio.handle.ticket.common.exception.CreateImagePortException;
import dev.jgregorio.handle.ticket.common.exception.CreateTicketCaseException;
import dev.jgregorio.handle.ticket.domain.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamSource;

import java.net.URL;
import java.util.Date;

@AllArgsConstructor
public class TicketService implements CreateTicketCase, CreateImageCase {

    private CreateTicketPort createTicketPort;
    private CreateImagePort createImagePort;

    @Override
    public Ticket createTicket(InputStreamSource ticketSource, String contentType)
            throws CreateTicketCaseException {
        if (ticketSource == null) {
            throw new IllegalArgumentException("Ticket source must not be empty");
        }
        try {
            return createTicketPort.createTicket(
                    Ticket.builder()
                            .source(createImage(ticketSource, contentType))
                            .created(new Date())
                            .build());
        } catch (Exception e) {
            // TODO log error
            throw new CreateTicketCaseException(e);
        }
    }

    @Override
    public URL createImage(InputStreamSource imageSource, String contentType)
            throws CreateImagePortException {
        return createImagePort.createImage(imageSource, contentType);
    }
}
