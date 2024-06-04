package org.jargus.analyze.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author Bazhov N.S.
 */
@Builder
@Getter
public class MetricAnalysisRule {
    String name;
    RuleCondition ruleCondition;
    List<String> flags;
    String annotation;
}
