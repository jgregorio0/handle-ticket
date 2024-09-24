package dev.jgregorio.handle.ticket;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;
import dev.jgregorio.handle.ticket.domain.model.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.FileSystemLoader;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.LoadTicketAdapter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//@SpringBootTest
class ParseTicketAdapterTest {

    @Test
    void givenImage_whenAnnotateImage_thenReturn() throws IOException {
        // GIVEN ticket with image in classpath
        Ticket ticket = Ticket.builder().
                imagePath("/ticket-mercadona.jpg").build();
        // WHEN load data
        LoadedTicket loadedData = new LoadTicketAdapter(
                new FileSystemLoader())
                .loadTicket(ticket);
        // THEN data loaded is not null
        assertNotNull(loadedData.getData());
        // WHEN parse data
        ParsedTicket parsedData = new dev.jgregorio.handle.ticket.infrastructure.api.ParseTicketAdapter(
                new GoogleVisionApi())
                .parseTicket(loadedData);
        // THEN data parsed is not null
        assertNotNull(parsedData.getData());
    }

}
