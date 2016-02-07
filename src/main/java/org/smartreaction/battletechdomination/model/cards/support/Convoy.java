package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Convoy extends Support {
    public Convoy() {
        name = "Convoy";
        cardText = "+5 Cards. Discard 3 cards. Your opponent may draw a card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(5);
        player.discardCards(3, false);
        //todo make draw card optional or change card ability to always have opponent draw card
        player.getOpponent().drawCards(1);
    }
}
