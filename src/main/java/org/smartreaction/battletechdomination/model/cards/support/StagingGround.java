package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class StagingGround extends Support {
    public StagingGround() {
        name = "Staging Ground";
        cardText = "+3 Cards. +1 Action. Discard 3 cards.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.addActions(1);
        player.discardCardsFromHand(3, false);
    }
}
