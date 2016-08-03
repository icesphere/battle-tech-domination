package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class RepairFacility extends Support {
    public RepairFacility() {
        name = "Repair Facility";
        cardText = "Draw cards from your deck until you have 6 cards in your hand. If you have fewer units in your deployment zone than your opponent, +1 Action.";
        industryCost = 4;
        imageFile = "RepairFacility.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawHandTo(6);
        if (player.getNumUnitsInDeploymentZone() < player.getOpponent().getNumUnitsInDeploymentZone()) {
            player.addActions(1);
        }
    }
}
