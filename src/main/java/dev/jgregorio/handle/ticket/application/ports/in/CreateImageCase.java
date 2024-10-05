package dev.jgregorio.handle.ticket.application.ports.in;

import dev.jgregorio.handle.ticket.common.exception.CreateImagePortException;
import org.springframework.core.io.InputStreamSource;

import java.net.URL;

/**
 * AS user
 * I WANT TO create image
 * SO I can use JPG, PNG as source data for ticket
 */
public interface CreateImageCase {
    URL createImage(InputStreamSource imageSource, String contentType) throws CreateImagePortException;
}
