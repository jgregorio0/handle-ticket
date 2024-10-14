package dev.jgregorio.handle.ticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.jgregorio.handle.ticket.common.utils.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Ticket {

    private Long id;
    private URL source;
    @JsonFormat(pattern = DateUtils.DATE_PATTERN)
    private LocalDate createdAt;

}
