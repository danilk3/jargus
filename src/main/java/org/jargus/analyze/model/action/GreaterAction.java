package org.jargus.analyze.model.action;

/**
 * @author Bazhov N.S.
 */
public class GreaterAction implements Action {
    @Override
    public boolean doAction(double leftValue, double rightValue) {
        return leftValue > rightValue;
    }
}
