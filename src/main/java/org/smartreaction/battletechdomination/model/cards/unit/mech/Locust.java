package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Mobile;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Spotter;

public class Locust extends MechUnit implements Spotter {
    public Locust() {
        name = "Locust";
        subName = "LCT-1V";
        cardText = "MOBILE: +1 Action when you deploy this unit. SPOTTER: When your opponent gains an Overrun card, he must place it on top of his deck.";
        attack = 0;
        defense = 0;
        industryCost = 0;
        imageFile = "Locust.png";

        addAbility(new Mobile(this));
    }
}
