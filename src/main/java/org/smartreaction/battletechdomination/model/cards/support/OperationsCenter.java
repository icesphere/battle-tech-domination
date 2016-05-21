package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class OperationsCenter extends Support {
    public OperationsCenter() {
        name = "Operations Center";
        cardText = "+1 Action. Discard any number of cards from your hand. +1 Card per card discarded in this way.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        //todo
    }
}
