package org.jargus.alert.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jargus.alert.model.Event;
import org.jargus.alert.model.Message;
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
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String body = defaultClient.post().uri(alertingConfig.getHost() + alertingConfig.getPath()).contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(event.getMessage())).retrieve().body(String.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
