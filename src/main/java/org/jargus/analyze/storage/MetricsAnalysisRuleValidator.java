package org.jargus.analyze.storage;

import org.jargus.alert.model.Event;
import org.jargus.common.model.Metric;
import org.jargus.scheduler.domain.TaskRequestModel;

/**
 * @author Bazhov N.S.
 */
public interface MetricsAnalysisRuleValidator {
    Event matches(Metric metric, TaskRequestModel taskRequestModel);
}
