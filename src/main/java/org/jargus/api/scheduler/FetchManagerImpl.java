package org.jargus.api.scheduler;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

/**
 * @author Bazhov N.S.
 */
@RequiredArgsConstructor
public class FetchManagerImpl implements FetchManager {
    private final HashMap<String, Fetcher> collectSchedulers;


    @Override
    public String getUri(String fetchName) {
        return collectSchedulers.get(fetchName).getFetch().getTarget() + collectSchedulers.get(fetchName).getFetch().getPath();
    }
}
