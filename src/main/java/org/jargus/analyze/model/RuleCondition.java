package org.jargus.analyze.model;

import org.jargus.analyze.model.action.Action;
import org.jargus.analyze.model.action.GreaterAction;
import org.jargus.analyze.model.action.LessAction;

/**
 * @author Bazhov N.S.
 */
public class RuleCondition {
    private Action ruleAction;

    public RuleCondition(String condition){

        char action = condition.charAt(0);
        double value = Double.parseDouble(condition.substring(1));

        switch (action) {
            case '>':
                ruleAction = new GreaterAction(value);
                break;
            case '<':
                ruleAction = new LessAction(value);
                break;
        }
    }

    public boolean check(double value){
        return ruleAction.doAction(value);
    }
}
