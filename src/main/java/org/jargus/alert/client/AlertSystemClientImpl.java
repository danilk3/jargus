package org.jargus.alert.client;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.model.Event;
import org.jargus.configuration.model.AlertingConfig;
import org.jargus.configuration.model.AppConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @author Bazhov N.S.
 */
@Component
public class AlertSystemClientImpl implements AlertSystemClient {
   RestClient defaultClient;
   private final AppConfig appConfig;

    public AlertSystemClientImpl(AppConfig appConfig) {
        defaultClient = RestClient.create();
        this.appConfig = appConfig;
    }

    @Override
    public void alert(Event event) {
        AlertingConfig alertingConfig = appConfig.getAlertingConfig();
        if (alertingConfig != null){
            String body = defaultClient.post().uri(alertingConfig.getHost() + alertingConfig.getPath()).contentType(MediaType.TEXT_PLAIN).body(event.getMessage().toString()).retrieve().body(String.class);
        }
    }
}
