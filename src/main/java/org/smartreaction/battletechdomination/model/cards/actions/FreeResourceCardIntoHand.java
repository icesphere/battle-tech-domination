package org.smartreaction.battletechdomination.model.cards.actions;

public class FreeResourceCardIntoHand extends Action {
    private Integer maxCost;

    public FreeResourceCardIntoHand(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
