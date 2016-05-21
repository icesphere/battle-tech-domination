package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class CombatDrop extends Support {
    public CombatDrop() {
        name = "Combat Drop";
        cardText = "+3 Cards. Discard any number of cards from your hand. +1 Action per card discarded this way.";
        industryCost = 6;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        //todo
    }
}
