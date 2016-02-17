package org.smartreaction.battletechdomination.model.cards.actions;

public class DamageUnitMinCost implements Action {
    private int minCost;

    public DamageUnitMinCost(int minCost) {
        this.minCost = minCost;
    }

    public int getMinCost() {
        return minCost;
    }
}
