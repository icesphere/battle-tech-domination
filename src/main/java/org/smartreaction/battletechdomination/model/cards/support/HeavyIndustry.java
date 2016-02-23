package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class HeavyIndustry extends Support {
    public HeavyIndustry() {
        name = "Heavy Industry";
        cardText = "Gain a card in the Supply without paying its cost and put it on top of your deck.";
        industryCost = 6;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.acquireFreeCardInSupplyAndPutOnTopOfDeck(null);
    }
}
