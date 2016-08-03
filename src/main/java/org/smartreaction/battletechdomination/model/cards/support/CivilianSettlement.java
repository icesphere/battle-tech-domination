package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class CivilianSettlement extends Support {
    public CivilianSettlement() {
        name = "Civilian Settlement";
        cardText = "+5 Cards. Discard 3 cards. Your opponent draws a card.";
        industryCost = 5;
        imageFile = "CivilianSettlement.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(5);
        player.discardCardsFromHand(3);
        player.getOpponent().drawCards(1);
    }
}
