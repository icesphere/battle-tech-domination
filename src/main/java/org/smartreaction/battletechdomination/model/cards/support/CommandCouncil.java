package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class CommandCouncil extends Support {
    public CommandCouncil() {
        name = "Command Council";
        cardText = "+3 Cards. +1 Action.";
        industryCost = 4;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.addActions(1);
    }
}
