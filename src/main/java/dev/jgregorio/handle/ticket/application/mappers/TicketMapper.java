package dev.jgregorio.handle.ticket.application.mappers;

import dev.jgregorio.handle.ticket.domain.Ticket;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.database.TicketEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper {

    TicketEntity toEntity(Ticket ticket);

    Ticket toModel(TicketEntity entity);
}
