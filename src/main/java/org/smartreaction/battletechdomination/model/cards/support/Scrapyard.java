package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class Scrapyard extends Support {
    public Scrapyard() {
        name = "Scrapyard";
        cardText = "+2 Industry. Choose one: Choose a card in the Scrapheap and gain it; or, Choose up to two cards from your discard pile and shuffle them into your deck.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
    }

    //todo
}
