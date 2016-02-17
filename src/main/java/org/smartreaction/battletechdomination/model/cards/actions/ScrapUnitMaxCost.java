package org.smartreaction.battletechdomination.model.cards.actions;

public class ScrapUnitMaxCost implements Action {
    private int maxCost;

    public ScrapUnitMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
