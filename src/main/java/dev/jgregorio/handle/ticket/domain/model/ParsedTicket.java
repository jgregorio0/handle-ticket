package dev.jgregorio.handle.ticket.domain.model;

import com.google.cloud.vision.v1.EntityAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParsedTicket {

    // TODO extract to rows private List<List<String>> rows;
    EntityAnnotation data;
}
