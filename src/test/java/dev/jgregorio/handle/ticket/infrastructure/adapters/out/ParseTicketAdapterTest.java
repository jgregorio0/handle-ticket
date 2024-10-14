package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.api.GoogleVisionApiResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


class ParseTicketAdapterTest {

    @Mock
    private GoogleVisionApi googleVisionApi;

    @InjectMocks
    private ParseTicketAdapter parseTicketAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenTicketImageBytes_whenParseTicket_thenAnnotateImageShouldBeCalledWithTicketImageBytes() throws IOException {
        // GIVEN ticket image bytes
        byte[] inputData = "sample-image-data".getBytes();
        // AND ticket loaded with image bytes
        LoadedTicket loadedTicket = LoadedTicket.builder()
                .data(inputData).build();
        // WHEN mock annotate image to return a successful list of annotations
        String expectedAnnotation = "expected-annotation";
        when(googleVisionApi.annotateImage(inputData))
                .thenReturn(List.of(
                        GoogleVisionApiResponses.buildSuccessAnnotation(expectedAnnotation)));
        // AND parse ticket
        parseTicketAdapter.parseTicket(loadedTicket);
        // THEN annotate image should be called with ticket image bytes
        then(googleVisionApi)
                .should().annotateImage(inputData);
    }

    @Test
    void givenTicketImageBytes_whenAnnotateImage() throws IOException {
        // GIVEN ticket image data
        byte[] ticketImageBytes = "Sample image data".getBytes();
        // AND loaded ticket
        LoadedTicket loadedTicket = LoadedTicket.builder().data(ticketImageBytes).build();
        // WHEN mock annotate image to throw exception
        doThrow(new IOException("Mocked IO Exception")).when(googleVisionApi).annotateImage(ticketImageBytes);
        // AND parse ticket
        Executable executable = () -> parseTicketAdapter.parseTicket(loadedTicket);
        // THEN exception should be thrown
        assertThrows(IOException.class, executable);
        // AND annotate image should be called
        then(googleVisionApi).should().annotateImage(ticketImageBytes);
    }

}
