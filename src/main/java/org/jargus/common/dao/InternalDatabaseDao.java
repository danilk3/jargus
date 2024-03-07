package org.jargus.common.dao;

import org.jargus.common.dto.ModuleRequest;
import org.jargus.common.dto.ModuleResponse;

/**
 * @author Bazhov N.S.
 */
public interface InternalDatabaseDao {
    ModuleResponse getMetrics(ModuleRequest request);
}
