package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.OverrunSupport;

public class HeavyCasualties extends OverrunSupport {
    public HeavyCasualties() {
        name = "Heavy Casualties";
        cardText = "You may discard an Infantry Platoon from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 1;
    }
}
