package dev.jgregorio.handle.ticket.common.config;

import dev.jgregorio.handle.ticket.infrastructure.api.GoogleVisionApi;
import dev.jgregorio.handle.ticket.infrastructure.api.ParseTicketAdapter;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.FileSystemLoader;
import dev.jgregorio.handle.ticket.infrastructure.filesystem.LoadTicketAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public LoadTicketAdapter loadTicketAdapter() {
        return new LoadTicketAdapter(
                new FileSystemLoader());
    }

    @Bean
    public ParseTicketAdapter parseTicketAdapter(){
        return new ParseTicketAdapter(
                new GoogleVisionApi());
    }

}
