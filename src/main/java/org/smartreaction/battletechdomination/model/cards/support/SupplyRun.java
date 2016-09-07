package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class SupplyRun extends Support {
    public SupplyRun() {
        name = "Supply Run";
        cardText = "Gain a card costing up to 4 Industry. Put the gained card on top of your deck.";
        industryCost = 5;
        imageFile = "SupplyRun.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.acquireFreeCardInSupplyAndPutOnTopOfDeck(4);
    }
}
