package org.jargus.analyze.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * @author Bazhov N.S.
 */
@Builder
@Getter
public class MetricAnalysisRule {
    String name;
    RuleCondition ruleCondition;
    String label;
    String message;
//    Flags flags;
    String annotation;
}
