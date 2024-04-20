package org.jargus.analyze.model.action;

/**
 * @author Bazhov N.S.
 */
public class EqualAction extends Action {
    public EqualAction(double actionValue) {
        super(actionValue);
    }

    @Override
    public boolean doAction(double leftValue) {
        return leftValue == actionValue;
    }
}
