package org.smartreaction.battletechdomination.model.cards.actions;

public class FreeCardFromSupplyToTopOfDeck implements Action {
    private Integer maxCost;

    public FreeCardFromSupplyToTopOfDeck(Integer maxCost) {
        this.maxCost = maxCost;
    }

    public Integer getMaxCost() {
        return maxCost;
    }
}
