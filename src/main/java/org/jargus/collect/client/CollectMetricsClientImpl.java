package org.jargus.collect.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Bazhov N.S.
 */
@Component
public class CollectMetricsClientImpl implements CollectMetricsClient {
    RestClient defaultClient;

    public CollectMetricsClientImpl() {
        this.defaultClient = RestClient.create();
    }

    @Override
    public List<String> export(String uri) {

        // TODO: add exception handling

        String body = Objects.requireNonNull(defaultClient.get().uri(uri).retrieve().body(String.class));

        return Arrays.stream(body.split("\n")).toList();
    }
}
