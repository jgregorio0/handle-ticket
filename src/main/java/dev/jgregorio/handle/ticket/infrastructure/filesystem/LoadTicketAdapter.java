package dev.jgregorio.handle.ticket.infrastructure.filesystem;

import dev.jgregorio.handle.ticket.application.ports.out.LoadTicketPort;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@AllArgsConstructor
@Component
public class LoadTicketAdapter implements LoadTicketPort {

    FileSystemLoader fileSystemLoader;

    @Override
    public LoadedTicket loadTicket(Ticket ticket) {
        if (ObjectUtils.isEmpty(ticket.getImagePath())) {
            throw new IllegalArgumentException("Ticket image path must not be empty");
        }
        return new LoadedTicket(
                // read bytes from ticket
                fileSystemLoader.readFileBytesByPath(
                        // get ticket file path
                        ticket.getImagePath()));
    }
}
