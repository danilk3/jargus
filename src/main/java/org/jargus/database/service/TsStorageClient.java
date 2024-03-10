package org.jargus.database.service;

import org.jargus.common.pojo.Metric;

/**
 * @author Kotelnikov D.M.
 */
public interface TsStorageClient {

    public void addDataPoint(Metric metric);

}
