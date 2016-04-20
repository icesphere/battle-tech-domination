package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class SupplyRoute extends Support {
    public SupplyRoute() {
        name = "Supply Route";
        cardText = "Gain a card costing up to 4 Industry. Put the gained card on top of your deck.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.acquireFreeCardInSupplyAndPutOnTopOfDeck(4);
    }
}
