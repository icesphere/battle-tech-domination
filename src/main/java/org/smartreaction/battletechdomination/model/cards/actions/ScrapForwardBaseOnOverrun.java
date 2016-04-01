package org.smartreaction.battletechdomination.model.cards.actions;

public class ScrapForwardBaseOnOverrun extends Action {
    private int difference;

    public ScrapForwardBaseOnOverrun(int difference, String text) {
        this.difference = difference;
        this.text = text;
    }

    public int getDifference() {
        return difference;
    }
}
