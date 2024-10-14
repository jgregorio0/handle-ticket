package dev.jgregorio.handle.ticket.domain.services;

import dev.jgregorio.handle.ticket.TestConstants;
import dev.jgregorio.handle.ticket.application.ports.out.CreateImagePort;
import dev.jgregorio.handle.ticket.application.ports.out.CreateTicketPort;
import dev.jgregorio.handle.ticket.common.exception.CreateImagePortException;
import dev.jgregorio.handle.ticket.common.exception.CreateTicketCaseException;
import dev.jgregorio.handle.ticket.domain.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;

class TicketServiceTest {

    @Mock
    private CreateTicketPort createTicketPort;

    @Mock
    private CreateImagePort createImagePort;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenImage_whenCreateTicket_thenReturnExpectedTicket() throws IOException, URISyntaxException, CreateImagePortException, CreateTicketCaseException {
        // GIVEN image
        //TICKET_URL
        // mock image multipart file
        MockMultipartFile file = new MockMultipartFile(
                "file", TestConstants.TICKET_PATH, MediaType.IMAGE_JPEG_VALUE,
                Files.readAllBytes(Paths.get(TestConstants.TICKET_URL.toURI())));
        // expected ticket
        Ticket expectedTicket = Ticket.builder()
                .source(TestConstants.TICKET_URL)
                .createdAt(LocalDate.now())
                .build();
        // mock createImage
        given(createImagePort.createImage(any(InputStreamSource.class), anyString()))
                .willReturn(TestConstants.TICKET_URL);
        // mock createTicket
        given(createTicketPort.createTicket(any(Ticket.class)))
                .willReturn(expectedTicket);
        // WHEN create ticket
        Ticket actualTicket = ticketService.createTicket(file.getResource(), file.getContentType());
        // THEN ticket as expected
        assertNotNull(actualTicket);
        assertEquals(expectedTicket.getSource(), actualTicket.getSource());
        assertEquals(expectedTicket.getCreatedAt(), actualTicket.getCreatedAt());
    }

    @Test
    void givenNullImage_whenCreateTicket_ThenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(null, MediaType.IMAGE_JPEG_VALUE));
    }

    @Test
    void givenImage_whenCreateTicketThrowsError_ThenIsCreateTicketCaseException() throws Exception {
        // GIVEN image
        // mock image multipart file
        MockMultipartFile file = new MockMultipartFile(
                "file", TestConstants.TICKET_PATH, MediaType.IMAGE_JPEG_VALUE,
                Files.readAllBytes(Paths.get(TestConstants.TICKET_URL.toURI())));
        // mock create ticket to throw Exception
        doThrow(new RuntimeException())
                .when(createTicketPort)
                .createTicket(any(Ticket.class));
        // WHEN create ticket
        Executable executable = () -> ticketService.createTicket(file.getResource(), file.getContentType());
        // THEN throws CreateTicketCaseException
        CreateTicketCaseException exception = assertThrows(CreateTicketCaseException.class,
                executable);
        // AND cause is RuntimeException
        assertInstanceOf(RuntimeException.class, exception.getCause());
    }

    @Test
    void givenImage_whenCreateImage_thenExpectedImageURL() throws CreateImagePortException, IOException, URISyntaxException {
        // GIVEN mock image multipart file
        MockMultipartFile file = new MockMultipartFile(
                "file", TestConstants.TICKET_PATH, MediaType.IMAGE_JPEG_VALUE,
                Files.readAllBytes(Paths.get(TestConstants.TICKET_URL.toURI())));
        // AND expected ticket
        URL expectedUrl = new URL("http://example.com/image");
        // mock create image
        given(createImagePort.createImage(any(InputStreamSource.class), anyString()))
                .willReturn(expectedUrl);
        // WHEN create image
        URL actualUrl = ticketService.createImage(file.getResource(), file.getContentType());
        // THEN expected URL
        assertEquals(expectedUrl, actualUrl);
    }
}
