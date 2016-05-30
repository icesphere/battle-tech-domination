package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class TacticalRedeployment extends Support implements SupportActionChoice {
    public TacticalRedeployment() {
        name = "Tactical Redeployment";
        cardText = "+1 Card. +1 Action. You may take a unit from your deployment zone and put it into your hand.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(1);
        player.makeYesNoSupportActionChoice(this, "Do you want to take a unit from your deployment zone and put it into your hand?");
    }

    @Override
    public void supportActionChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.moveUnitFromDeploymentZoneToHand();
        }
    }
}
