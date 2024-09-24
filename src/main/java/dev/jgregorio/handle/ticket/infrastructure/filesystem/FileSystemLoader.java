package dev.jgregorio.handle.ticket.infrastructure.filesystem;

import dev.jgregorio.handle.ticket.common.exception.FileSystemResourceReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileSystemLoader {

    public byte[] readFileBytesByPath(String filePath) {
        // Return loaded data file
        return Optional.ofNullable(
                        // get file path resource
                        getClass().getResource(filePath))
                .map(url -> {
                    try {
                        // read file bytes
                        return Files.readAllBytes(Path.of(url.getPath()));
                    } catch (IOException e) {
                        // TODO log error
                        throw new FileSystemResourceReadException("Resource could not be read: " + filePath);
                    }
                })
                .orElseThrow(() -> new FileSystemResourceReadException("Resource not found: " + filePath));
    }
}
