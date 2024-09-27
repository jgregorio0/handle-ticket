package dev.jgregorio.handle.ticket.domain.model;

import dev.jgregorio.handle.ticket.infrastructure.database.TicketEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Ticket {

    private Long id;
    private String imagePath;
    private Date created;

}
