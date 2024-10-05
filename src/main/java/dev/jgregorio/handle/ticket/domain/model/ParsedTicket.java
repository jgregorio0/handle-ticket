package dev.jgregorio.handle.ticket.domain.model;

import com.google.cloud.vision.v1.EntityAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParsedTicket {

    // TODO extract to rows private List<List<String>> rows;
    List<EntityAnnotation> datas;
}
