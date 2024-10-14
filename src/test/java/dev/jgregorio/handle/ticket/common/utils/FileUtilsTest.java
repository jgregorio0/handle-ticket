package dev.jgregorio.handle.ticket.common.utils;

import dev.jgregorio.handle.ticket.TestConstants;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class FileUtilsTest {

    @Test
    void giveExistingFile_whenRead_ThenReturnString() throws IOException {
        String fileString = FileUtils.readLargeFileStringByUrl(
                TestConstants.class.getResource(TestConstants.TICKET_PATH));
        assertNotNull(fileString);
    }
}