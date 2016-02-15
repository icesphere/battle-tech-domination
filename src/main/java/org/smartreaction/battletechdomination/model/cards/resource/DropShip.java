package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class DropShip extends Resource {
    public DropShip() {
        name = "Dropship";
        cardText = "+3 Cards. Put a card from your hand on top of your deck.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.addCardsFromHandToTopOfDeck(1);
    }
}
