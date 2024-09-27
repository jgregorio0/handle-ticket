package dev.jgregorio.handle.ticket.infrastructure.database;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class TicketEntity {
    Long id;
    Date created;
}
