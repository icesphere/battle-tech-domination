package org.smartreaction.battletechdomination.model.cards.actions;

public class FreeCardFromSupplyToTopOfDeck extends Action {
    private Integer maxCost;

    public FreeCardFromSupplyToTopOfDeck(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public Integer getMaxCost() {
        return maxCost;
    }
}
