package org.jargus.alert.client;

import org.jargus.alert.model.Event;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

/**
 * @author Bazhov N.S.
 */
@Component
public class AlertSystemClientImpl implements AlertSystemClient {
   RestClient defaultClient;

    public AlertSystemClientImpl() {
        defaultClient = RestClient.create();
    }

    @Override
    public void alert(Event event) {
        String body = defaultClient.post().uri(event.getUrl()).contentType(MediaType.TEXT_PLAIN).body(event.getMessage().toString()).retrieve().body(String.class);
    }
}
