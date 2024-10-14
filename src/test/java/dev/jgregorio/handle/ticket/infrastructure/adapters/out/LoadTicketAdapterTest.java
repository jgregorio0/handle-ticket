package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.TestConstants;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem.FileSystemLoader;
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
        Ticket ticket = Ticket.builder()
                .source(TestConstants.class.getResource(
                        TestConstants.TICKET_PATH))
                .build();
        // WHEN load data
        LoadedTicket loadedData = loadTicketAdapter
                .loadTicket(ticket);
        // THEN data loaded is not null
        assertNotNull(loadedData.getData());
    }

    @Test
    void givenNonExistingImageTicket_whenLoadTicket_thenErrorIsThrown() {
        // GIVEN ticket that not exists image in classpath
        Ticket ticket = Ticket.builder()
                .source(TestConstants.class.getResource(
                        TestConstants.NON_EXISTING_TICKET_PATH))
                .build();
        // WHEN load ticket data
        Executable loadTicketExecutable = () -> loadTicketAdapter
                .loadTicket(ticket);
        // THEN error is thrown
        assertThrows(RuntimeException.class, loadTicketExecutable);
    }

}
