package dev.jgregorio.handle.ticket;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handle.ticket.common.config.TicketConfig;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.ParseTicketAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@SpringBootTest(classes = TicketConfig.class)
class ParseTicketAdapterTest {

    @Mock
    private GoogleVisionApi googleVisionApi;

    @InjectMocks
    private ParseTicketAdapter parseTicketAdapter;

    @Test
    void givenTicketImageInputAndApiJsonOutput_whenParseTicket_thenAnnotateImage() throws IOException {
        // GIVEN
        // input loaded ticket
//        byte[] data = new FileSystemLoader()
//                .readFileBytesByPath(
//                        TestConstants.class.getResource(
//                                TestConstants.TICKET_PATH));
        // GIVEN
        // input ticket data
        byte[] inputData = "fake-input-data".getBytes();
        // output json response from Google Vision API
//        List<EntityAnnotation> jsonResponse = new Gson()
//                .fromJson(
//                        new FileReader(
//                                TestConstants.class.getResource(
//                                        TestConstants.TICKET_JSON_PATH).getPath()),
//                        new TypeToken<List<EntityAnnotation>>() {
//                        }.getType());
        List<EntityAnnotation> outputData = List.of(
                EntityAnnotation.newBuilder().build(),
                EntityAnnotation.newBuilder().build());
        // mock annotate image
        given(googleVisionApi.annotateImage(inputData))
                .willReturn(outputData);
        // WHEN
        // parse ticket
        parseTicketAdapter.parseTicket(LoadedTicket.builder()
                .data(inputData).build());
        // THEN
        // Google Vision API annotates image
        then(googleVisionApi)
                .should().annotateImage(inputData);
    }

}
