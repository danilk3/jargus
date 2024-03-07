package org.jargus.collect.controller;

import org.jargus.common.dto.ModuleRequest;
import org.jargus.common.dto.ModuleResponse;

/**
 * @author Bazhov N.S.
 */
public interface MetricsRequestController {
    ModuleResponse exportMetricsFromSidecar(ModuleRequest request);
    ModuleResponse exportMetricsFromInternalDatabase(ModuleRequest request);
}
