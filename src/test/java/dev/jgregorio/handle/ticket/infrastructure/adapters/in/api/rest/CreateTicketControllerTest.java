package dev.jgregorio.handle.ticket.infrastructure.adapters.in.api.rest;

import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.domain.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreateTicketController.class)
class CreateTicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TicketService ticketService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void givenImage_whenCreateTicket_thenCreateImageAndCreateTicket()
            throws Exception {
        // GIVEN image
        String TICKET_IMAGE = "/ticket-mercadona.jpg";
        URL imageUrl = Objects.requireNonNull(this.getClass().getResource(TICKET_IMAGE));
        // mock file to create ticket
        MockMultipartFile file = new MockMultipartFile(
                "file", TICKET_IMAGE, MediaType.IMAGE_JPEG_VALUE,
                Files.readAllBytes(Paths.get(imageUrl.toURI())));
        // return ticket
        LocalDate createdDate = LocalDate.now();
        given(ticketService.createTicket(file, MediaType.IMAGE_JPEG_VALUE))
                .willReturn(Ticket.builder()
                        .createdAt(createdDate)
                        .id(1L)
                        .source(imageUrl)
                        .build());
        // WHEN create ticket
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(HttpMethod.POST, "/api/v1/tickets")
                        .file(file))
                // THEN return 200 OK
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.source").value(imageUrl.toString()))
                .andExpect(jsonPath("$.created").value(formatter.format(createdDate)));
    }
}