package org.smartreaction.battletechdomination.model.cards.actions;

public class FreeResourceCardIntoHand implements Action {
    private int maxCost;

    public FreeResourceCardIntoHand(int maxCost) {
        this.maxCost = maxCost;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
