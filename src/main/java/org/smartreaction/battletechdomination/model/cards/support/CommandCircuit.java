package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class CommandCircuit extends Support {
    public CommandCircuit() {
        name = "Command Circuit";
        cardText = "+2 Cards. +1 Action. When you buy this card, gain a card costing 5 Industry or less and put it on top of your deck.";
        industryCost = 4;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(2);
        player.addActions(1);
    }

    @Override
    public void cardBought(Player player) {
        player.acquireFreeCardInSupplyAndPutOnTopOfDeck(5);
    }
}
