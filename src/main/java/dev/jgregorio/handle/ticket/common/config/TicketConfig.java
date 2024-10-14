package dev.jgregorio.handle.ticket.common.config;

import dev.jgregorio.handle.ticket.application.mappers.TicketMapper;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.CreateImageAdapter;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.CreateTicketAdapter;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.LoadTicketAdapter;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.ParseTicketAdapter;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.database.TicketRepository;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem.FileSystemLoader;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem.FileSystemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketConfig {

    @Bean
    public LoadTicketAdapter loadTicketAdapter() {
        return new LoadTicketAdapter(
                new FileSystemLoader());
    }

    @Bean
    public ParseTicketAdapter parseTicketAdapter() {
        return new ParseTicketAdapter(
                new GoogleVisionApi());
    }

    @Bean
    public CreateTicketAdapter createTicketAdapter(
            TicketRepository ticketRepository, TicketMapper ticketMapper) {
        return new CreateTicketAdapter(
                ticketRepository, ticketMapper);
    }

    @Bean
    public CreateImageAdapter createImageAdapter() {
        return new CreateImageAdapter(
                new FileSystemWriter());
    }
}
