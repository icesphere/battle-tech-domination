package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class SupplyRun extends Support {
    public SupplyRun() {
        name = "Supply Run";
        cardText = "+1 Action. Gain a card costing up to 4 Industry and place it on top of your deck.";
        industryCost = 5;
        imageFile = "SupplyRun2.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        player.acquireFreeCardInSupplyAndPutOnTopOfDeck(4);
    }
}
