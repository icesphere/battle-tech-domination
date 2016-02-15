package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class TacticalRedeployment extends Support {
    public TacticalRedeployment() {
        name = "Tactical Redeployment";
        cardText = "+1 Card. +1 Action. You may take a unit from your deployment zone and put it into your hand.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(1);
        player.addUnitFromDeploymentZoneToHand();
    }
}
