package dev.jgregorio.handle.ticket.infrastructure.adapters.out.filesystem;

import dev.jgregorio.handle.ticket.common.utils.FileUtils;

import java.io.IOException;
import java.net.URL;

public class FileSystemLoader {

    public byte[] readFileBytesByPath(URL fileUrl) throws IOException {
        // Return loaded data file
        return FileUtils.readFileBytesByUrl(fileUrl);
    }
}
