package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class RaidedSupplies extends OverrunSupport {
    public RaidedSupplies() {
        name = "Raided Supplies";
        cardText = "You may discard 2 cards from your hand.  If you do, return this card to the Overrun pile.";
        overrunAmount = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Discard 2 cards from your hand to return Raided Supplies to Overrun pile"));
    }
}
