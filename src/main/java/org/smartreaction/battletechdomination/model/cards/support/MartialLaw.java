package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class MartialLaw extends Support {
    public MartialLaw() {
        name = "Martial Law";
        cardText = "+2 Cards. +2 Actions. Discard a card.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(2);
        player.addActions(2);
        player.discardCardFromHand();
    }
}
