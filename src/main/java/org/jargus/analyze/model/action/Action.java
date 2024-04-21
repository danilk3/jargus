package org.jargus.analyze.model.action;

/**
 * @author Bazhov N.S.
 */
public abstract class Action {
    protected double actionValue;
    protected Action(double actionValue){
        this.actionValue = actionValue;
    }
    public abstract boolean doAction(double leftValue);
}
