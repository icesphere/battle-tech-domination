package org.smartreaction.battletechdomination.model.cards.actions;

public class DamageUnitMaxCost implements Action {
    private int maxCost;

    public DamageUnitMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
