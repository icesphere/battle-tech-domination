package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Convoy extends Support {
    public Convoy() {
        name = "Convoy";
        cardText = "+5 Cards. Discard 3 cards. Your opponent draws a card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(5);
        player.discardCardsFromHand(3, false);
        player.getOpponent().drawCards(1);
    }
}
