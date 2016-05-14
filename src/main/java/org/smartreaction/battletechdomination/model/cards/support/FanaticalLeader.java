package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class FanaticalLeader extends Support {
    public FanaticalLeader() {
        name = "Fanatical Leader";
        cardText = "+1 Card. +2 Actions. You may reveal any Overrun cards in your hand. +1 Card per card revealed in this way.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
    }

    //todo
}
