package org.jargus.analyze.model;

import org.jargus.analyze.model.action.Action;
import org.jargus.analyze.model.action.GreaterAction;

/**
 * @author Bazhov N.S.
 */
public class RuleCondition {
    private Action ruleAction;

    public RuleCondition(String condition){

        String action = condition.split(" ")[0];
        double value = Double.parseDouble(condition.split(" ")[1]);

        switch (action) {
            case "moreThan":
                ruleAction = new GreaterAction(value);
        }
    }

    public boolean check(double value){
        return ruleAction.doAction(value);
    }
}
