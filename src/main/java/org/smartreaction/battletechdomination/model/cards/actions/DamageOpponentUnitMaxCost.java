package org.smartreaction.battletechdomination.model.cards.actions;

public class DamageOpponentUnitMaxCost extends Action {
    private int maxCost;

    public DamageOpponentUnitMaxCost(int maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
