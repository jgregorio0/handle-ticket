package dev.jgregorio.handle.ticket;

import java.net.URL;
import java.util.Objects;

public class TestConstants {
    public static final String TICKET_PATH = "/ticket-mercadona.jpg";
    public static final String NON_EXISTING_TICKET_PATH = "/non-existing-ticket.jpg";
    public static final String TICKET_JSON_PATH = "/ticket-mercadona-text-annotations.json";
    public static final URL TICKET_URL = Objects.requireNonNull(TestConstants.class.getResource(TICKET_PATH));
}
