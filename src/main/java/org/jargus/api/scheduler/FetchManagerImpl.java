package org.jargus.api.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Bazhov N.S.
 */
@Component
@RequiredArgsConstructor
public class FetchManagerImpl implements FetchManager {
//    private final HashMap<String, Fetcher> collectSchedulers;
    private final Fetcher collectSchedulers;


    @Override
    public String getUri(String fetchName) {
//        return collectSchedulers.get(fetchName).getFetch().getTarget() + collectSchedulers.get(fetchName).getFetch().getPath();
        return collectSchedulers.getFetch().getFetches().getTarget() + collectSchedulers.getFetch().getFetches().getPath();
    }
}
