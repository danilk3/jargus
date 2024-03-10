package org.jargus.database.service;

import org.jargus.common.model.Metric;

/**
 * @author Kotelnikov D.M.
 */
public class TsStorageInMemoryClient implements TsStorageClient {

    private final TsStorage tsStorage;

    public TsStorageInMemoryClient(TsStorage tsStorage) {
        this.tsStorage = tsStorage;
    }


    @Override
    public void addDataPoint(Metric metric) {
        tsStorage.addDataPoint(metric);
    }
}
