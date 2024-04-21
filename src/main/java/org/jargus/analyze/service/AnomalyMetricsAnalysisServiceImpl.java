package org.jargus.analyze.service;

import lombok.RequiredArgsConstructor;
import org.jargus.alert.client.AlertSystemClient;
import org.jargus.alert.model.Event;
import org.jargus.analyze.storage.MetricsAnalysisRuleValidator;
import org.jargus.common.model.Metric;
import org.jargus.scheduler.domain.TaskRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Service
@RequiredArgsConstructor
public class AnomalyMetricsAnalysisServiceImpl implements AnomalyMetricsAnalysisService {
    private final MetricsAnalysisRuleValidator metricsAnalysisRuleValidator;
    private final AlertSystemClient alertSystemClient;

    @Override
    public void analyzeMetrics(List<Metric> metrics, TaskRequestModel taskRequestModel) {
        for (Metric metric: metrics) {

            Event event = metricsAnalysisRuleValidator.matches(metric, taskRequestModel);
            if (event != null && event.isPass()){
                alertSystemClient.alert(event);
            }
        }
    }
}
