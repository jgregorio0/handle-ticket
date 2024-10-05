package dev.jgregorio.handle.ticket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoadedTicket {

    // TODO extract to rows private List<List<String>> rows;
    byte[] data;
}
