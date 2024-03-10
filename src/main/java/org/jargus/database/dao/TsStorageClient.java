package org.jargus.database.dao;

import org.jargus.common.model.Metric;

/**
 * @author Kotelnikov D.M.
 */
public interface TsStorageClient {

    void addDataPoint(Metric metric);
}
