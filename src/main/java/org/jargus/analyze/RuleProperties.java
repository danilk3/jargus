package org.jargus.analyze;

import lombok.Data;
import org.jargus.analyze.model.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bazhov N.S.
 */
@Data
@ConfigurationProperties
@Component
public class RuleProperties {
    private Rule rule;
}
