package org.smartreaction.battletechdomination.model.cards.actions;

public class DamageUnitMinCost extends Action {
    private int minCost;

    public DamageUnitMinCost(int minCost, String text) {
        this.minCost = minCost;
        this.text = text;
    }

    public int getMinCost() {
        return minCost;
    }
}
