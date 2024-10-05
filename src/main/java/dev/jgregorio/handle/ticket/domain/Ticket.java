package dev.jgregorio.handle.ticket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Date;

@Getter
@Setter
@Builder
public class Ticket {

    private Long id;
    private URL source;
    private Date created;

}
