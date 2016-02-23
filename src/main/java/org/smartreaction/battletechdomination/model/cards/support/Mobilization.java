package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Mobilization extends Support {
    public Mobilization() {
        name = "Mobilization";
        cardText = "Draw cards from your deck until you have 6 cards in your hand. If you have fewer units in your deployment zone than your opponent, +1 Action.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawHandTo(6);
        if (player.getNumUnitsInDeploymentZone() < player.getOpponent().getNumUnitsInDeploymentZone()) {
            player.addActions(1);
        }
    }
}
