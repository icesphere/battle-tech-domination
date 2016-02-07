package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;

public class RaidedSupplies extends OverrunSupport {
    public RaidedSupplies() {
        name = "Raided Supplies";
        cardText = "You may discard 2 cards from your hand.  If you do, return this card to the Overrun pile.";
        overrunAmount = 2;
    }
}
