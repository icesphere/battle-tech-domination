package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class DamageUnitMinCost extends Action {
    private int minCost;

    public DamageUnitMinCost(int minCost, String text) {
        this.minCost = minCost;
        this.text = text;
    }

    public int getMinCost() {
        return minCost;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS) && card.getIndustryCost() >= minCost;
    }
}
