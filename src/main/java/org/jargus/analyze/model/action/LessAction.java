package org.jargus.analyze.model.action;

/**
 * @author Bazhov N.S.
 */
public class LessAction extends Action {
    public LessAction(double actionValue) {
        super(actionValue);
    }

    @Override
    public boolean doAction(double leftValue) {
        return leftValue < actionValue;
    }
}
