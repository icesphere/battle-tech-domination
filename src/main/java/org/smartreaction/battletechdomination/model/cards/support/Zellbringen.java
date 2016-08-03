package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Zellbringen extends Support {
    public Zellbringen() {
        name = "Zellbringen";
        cardText = "+2 Cards. +2 Actions. Discard a card.";
        industryCost = 4;
        imageFile = "Zellbringen.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(2);
        player.addActions(2);
        player.discardCardFromHand();
    }
}
