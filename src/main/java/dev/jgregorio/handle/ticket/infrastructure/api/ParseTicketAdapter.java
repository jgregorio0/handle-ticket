package dev.jgregorio.handle.ticket.infrastructure.api;

import dev.jgregorio.handle.ticket.application.ports.out.ParseTicketPort;
import dev.jgregorio.handle.ticket.domain.model.LoadedTicket;
import dev.jgregorio.handle.ticket.domain.model.ParsedTicket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class ParseTicketAdapter implements ParseTicketPort {

    GoogleVisionApi googleVisionApi;

    @Override
    public ParsedTicket parseTicket(LoadedTicket loadedTicket) throws IOException {
        // return parsed data
        return new ParsedTicket(
                // annotate image using Google Vision
                googleVisionApi.annotateImage(
                        // get ticket image bytes
                        loadedTicket.getData()));
    }
}
