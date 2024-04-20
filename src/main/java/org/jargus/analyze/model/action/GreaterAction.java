package org.jargus.analyze.model.action;

/**
 * @author Bazhov N.S.
 */
public class GreaterAction extends Action {

    public GreaterAction(double value) {
        super(value);

    }

    @Override
    public boolean doAction(double leftValue) {
        return leftValue > actionValue;
    }
}
