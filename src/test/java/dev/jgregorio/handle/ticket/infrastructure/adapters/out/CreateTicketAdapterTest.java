package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.TestConstants;
import dev.jgregorio.handle.ticket.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreateTicketAdapterTest {

    @Autowired
    CreateTicketAdapter createTicketAdapter;

    @Test
    public void testCreateTicket() {
        // GIVEN
        // ticket requested to create
        Ticket ticket = Ticket.builder()
                .createdAt(LocalDate.now())
                .source(TestConstants.class.getResource(TestConstants.TICKET_PATH))
                .build();
        // WHEN
        // ticket created
        Ticket created = createTicketAdapter.createTicket(ticket);
        // THEN
        // validate ticket attributes
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(ticket.getCreatedAt(), created.getCreatedAt());
        assertEquals(ticket.getSource(), created.getSource());
    }

}