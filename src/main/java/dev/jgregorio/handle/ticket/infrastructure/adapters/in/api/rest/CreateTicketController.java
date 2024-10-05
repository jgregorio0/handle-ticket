package dev.jgregorio.handle.ticket.infrastructure.adapters.in.api.rest;

import dev.jgregorio.handle.ticket.application.ports.in.CreateTicketCase;
import dev.jgregorio.handle.ticket.common.exception.CreateTicketCaseException;
import dev.jgregorio.handle.ticket.domain.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class CreateTicketController {

    private CreateTicketCase createTicketCase;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @RequestParam("file") MultipartFile file)
            throws CreateTicketCaseException {
        return new ResponseEntity<>(
                createTicketCase.createTicket(file, file.getContentType()),
                HttpStatus.CREATED);
    }
}
