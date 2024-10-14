package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.application.ports.out.LoadTicketPort;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem.FileSystemLoader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@AllArgsConstructor
@Component
public class LoadTicketAdapter implements LoadTicketPort {

    FileSystemLoader fileSystemLoader;

    @Override
    public LoadedTicket loadTicket(Ticket ticket) throws IOException {
        if (ObjectUtils.isEmpty(ticket.getSource())) {
            throw new IllegalArgumentException("Ticket image URL must not be empty");
        }
        return LoadedTicket.builder()
                .data(
                        // read bytes from ticket
                        fileSystemLoader.readFileBytesByPath(
                                // get ticket file path
                                ticket.getSource()))
                .build();
    }
}
