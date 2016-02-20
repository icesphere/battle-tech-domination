package org.smartreaction.battletechdomination.model.cards.actions;

public class ScrapForwardBaseOnOverrun implements Action {
    private int difference;

    public ScrapForwardBaseOnOverrun(int difference) {
        this.difference = difference;
    }

    public int getDifference() {
        return difference;
    }
}
