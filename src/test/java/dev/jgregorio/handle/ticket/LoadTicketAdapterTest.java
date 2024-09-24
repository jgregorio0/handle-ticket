package dev.jgregorio.handle.ticket;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.FileSystemLoader;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.LoadTicketAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LoadTicketAdapterTest {

    // initialize file system loader
    LoadTicketAdapter loadTicketAdapter = new LoadTicketAdapter(
            new FileSystemLoader());

    @Test
    void givenExistingImageTicket_whenLoadTicket_thenLoadedTicketDataIsNotNull() throws IOException {
        // GIVEN ticket with image in classpath
        Ticket ticket = Ticket.builder().
                imagePath("/ticket-mercadona.jpg").build();
        // WHEN load data
        LoadedTicket loadedData = loadTicketAdapter
                .loadTicket(ticket);
        // THEN data loaded is not null
        assertNotNull(loadedData.getData());
    }

    @Test
    void givenNonExistingImageTicket_whenLoadTicket_thenErrorIsThrown() {
        // GIVEN ticket with image in classpath
        Ticket ticket = Ticket.builder().
                imagePath("/non-existing-image.jpg").build();
        // WHEN load ticket data
        Executable loadTicketExecutable = () -> loadTicketAdapter
                .loadTicket(ticket);
        // THEN error is thrown
        assertThrows(RuntimeException.class, loadTicketExecutable);
    }

}
