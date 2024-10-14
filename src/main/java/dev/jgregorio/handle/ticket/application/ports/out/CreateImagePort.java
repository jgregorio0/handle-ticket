package dev.jgregorio.handle.ticket.application.ports.out;

import dev.jgregorio.handle.ticket.common.exception.CreateImagePortException;
import org.springframework.core.io.InputStreamSource;

import java.net.URL;

public interface CreateImagePort {
    URL createImage(InputStreamSource imageSource, String contentType) throws CreateImagePortException;
}
