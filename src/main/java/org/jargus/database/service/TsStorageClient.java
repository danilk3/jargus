package org.jargus.database.service;

import org.jargus.common.model.Metric;

/**
 * @author Kotelnikov D.M.
 */
public interface TsStorageClient {

    public void addDataPoint(Metric metric);

}
