package dev.jgregorio.handle.ticket.common.utils;

import dev.jgregorio.handle.ticket.common.exception.FileSystemResourceReadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileUtils {

    public static String readLargeFileStringByUrl(URL fileUrl) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileUrl.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] readFileBytesByUrl(URL fileUrl) throws FileSystemResourceReadException {
        return Optional.ofNullable(fileUrl)
                .map(url -> {
                    try {
                        // read file bytes
                        return Files.readAllBytes(Path.of(url.getPath()));
                    } catch (IOException e) {
                        // TODO log error
                        throw new FileSystemResourceReadException("Resource could not be read: " + fileUrl);
                    }
                })
                .orElseThrow(() -> new FileSystemResourceReadException("Resource not found: " + fileUrl));
    }
}
