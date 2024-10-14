package dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem;

import dev.jgregorio.handle.ticket.TicketApplication;
import dev.jgregorio.handle.ticket.common.exception.ContentTypeExtensionNotFoundException;
import org.springframework.core.io.InputStreamSource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class FileSystemWriter {

    private static final int BUFFER_SIZE = 8192;
    private static final String TICKET_IMAGE_FOLDER = Objects.requireNonNull(TicketApplication.class.getResource("/")).getPath(); //TODO set as property

    public URL writeFile(InputStreamSource input, String contentType) throws IOException, ContentTypeExtensionNotFoundException {
        // write file so it could be loaded afterward
        return writeBufferedFile(input,
                // generate unique filename so file could be saved in file system
                generateUniqueFileURL(
                        // get extension from content type so it is included in filename
                        getExtensionByContentType(contentType)));
    }

    private URL writeBufferedFile(InputStreamSource input, URL fileURL) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(input.getInputStream(), BUFFER_SIZE);
             FileOutputStream fos = new FileOutputStream(fileURL.getPath());
             BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE)) {
            // use buffer to improve performance
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException(
                    String.format("File %s could not be saved", fileURL.getPath()), e);
        }
        return fileURL;
    }

    private URL generateUniqueFileURL(String extension) throws MalformedURLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        while (true) {
            UUID uniqueId = UUID.randomUUID();
            String filename = String.format("%s_%s.%s",
                    timestamp,
                    uniqueId,
                    extension);
            // verify filename do not exists before return value
            File file = Paths.get(FileSystemWriter.TICKET_IMAGE_FOLDER, filename).toFile();
            if (!file.exists()) {
                return file.toURI().toURL();
            }
        }
    }

    private String getExtensionByContentType(String contentType) throws ContentTypeExtensionNotFoundException {
        return Optional.ofNullable(contentType)
                .map(s -> s.split("/"))
                .filter(parts -> parts.length > 1)
                .map(parts -> parts[1])
                .orElseThrow(() -> new ContentTypeExtensionNotFoundException(
                        String.format("Extension could not be found for content type %s", contentType)));
    }
}
