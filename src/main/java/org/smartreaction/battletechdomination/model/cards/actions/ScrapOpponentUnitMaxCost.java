package org.smartreaction.battletechdomination.model.cards.actions;

public class ScrapOpponentUnitMaxCost extends Action {
    private int maxCost;

    public ScrapOpponentUnitMaxCost(int maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
