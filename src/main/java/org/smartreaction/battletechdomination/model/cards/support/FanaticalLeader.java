package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class FanaticalLeader extends Support {
    public FanaticalLeader() {
        name = "Fanatical Leader";
        cardText = "+1 Card. +2 Actions. Reveal any Overrun cards in your hand. +1 Card per card revealed in this way.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(2);
        player.getHand().stream().forEach(c -> {
            if (c.isOverrun()) {
                player.addGameLog(player.getPlayerName() + " revealed " + c.getName() + " from their hand and gained +1 Card");
                player.drawCards(1);
            }
        });
    }
}
