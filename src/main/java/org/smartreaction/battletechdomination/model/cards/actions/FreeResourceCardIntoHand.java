package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class FreeResourceCardIntoHand extends Action {
    private Integer maxCost;

    public FreeResourceCardIntoHand(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY) && card.isResource() && (maxCost == null || card.getIndustryCost() <= maxCost);
    }
}
