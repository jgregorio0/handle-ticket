package dev.jgregorio.handle.ticket;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.api.ParseTicketAdapter;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.FileSystemLoader;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.LoadTicketAdapter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//@SpringBootTest
class ParseTicketAdapterTest {

    private LoadTicketAdapter loadTicketAdapter = new LoadTicketAdapter(
            new FileSystemLoader());
    ParseTicketAdapter parseTicketAdapter = new ParseTicketAdapter(
            new GoogleVisionApi());

    @Test
    void givenImage_whenAnnotateImage_thenReturn() throws IOException {
        // GIVEN ticket with image in classpath
        Ticket ticket = Ticket.builder().
                imagePath("/ticket-mercadona.jpg").build();
        // WHEN parse data
        LoadedTicket loadedTicket = loadTicketAdapter.loadTicket(ticket);
        ParsedTicket parsedTicket = parseTicketAdapter.parseTicket(loadedTicket);
        // THEN data parsed is not null
        assertNotNull(parsedTicket.getData());
    }

}
