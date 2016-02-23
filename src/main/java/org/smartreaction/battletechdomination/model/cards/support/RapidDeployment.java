package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class RapidDeployment extends Support {
    public RapidDeployment() {
        name = "Rapid Deployment";
        cardText = "+1 Card. +2 Actions.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(2);
    }
}
