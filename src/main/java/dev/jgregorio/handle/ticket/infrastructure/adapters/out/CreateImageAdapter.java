package dev.jgregorio.handle.ticket.infrastructure.adapters.out;

import dev.jgregorio.handle.ticket.application.ports.out.CreateImagePort;
import dev.jgregorio.handle.ticket.common.exception.CreateImagePortException;
import dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem.FileSystemWriter;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.URL;

@AllArgsConstructor
public class CreateImageAdapter implements CreateImagePort {

    FileSystemWriter fileSystemWriter;

    @Override
    public URL createImage(InputStreamSource imageSource, String contentType) throws CreateImagePortException {
        if (ObjectUtils.isEmpty(imageSource)) {
            throw new IllegalArgumentException("Image input stream must not be empty");
        }
        try {
            return fileSystemWriter.writeFile(imageSource, contentType);
        } catch (Exception e) {
            // TODO add log
            throw new CreateImagePortException(e);
        }
    }
}
