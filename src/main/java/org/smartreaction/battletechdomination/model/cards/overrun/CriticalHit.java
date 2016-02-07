package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;

public class CriticalHit extends OverrunSupport {
    public CriticalHit() {
        name = "Critical Hit";
        cardText = "You may scrap a Mech from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 3;
    }
}
