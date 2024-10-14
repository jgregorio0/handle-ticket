package dev.jgregorio.handle.ticket.common.utils;

import dev.jgregorio.handle.ticket.common.exception.FileSystemResourceReadException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtils {

    public static byte[] readFileAsBytesBuffered(URL fileUrl) {
        try (InputStream inputStream = fileUrl.openStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            // Handle exception appropriately
            throw new FileSystemResourceReadException("Resource could not be read: " + fileUrl);
        }
    }


    public static byte[] readFileBytesByUrl(URL fileUrl) throws FileSystemResourceReadException {
        return Optional.ofNullable(fileUrl)
                .map(url -> {
                    try {
                        // read file bytes
                        return Files.readAllBytes(Paths.get(url.toURI()));
                    } catch (IOException | URISyntaxException e) {
                        // TODO log error
                        throw new FileSystemResourceReadException("Resource could not be read: " + fileUrl);
                    }
                })
                .orElseThrow(() -> new FileSystemResourceReadException("Resource not found: " + fileUrl));
    }
}
