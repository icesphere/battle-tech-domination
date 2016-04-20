package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Mobile;

public class Cicada extends MechUnit implements Mobile {
    public Cicada() {
        name = "Cicada";
        subName = "CDA-2A";
        cardText = "MOBILE: +1 Action when you deploy this unit.";
        attack = 1;
        defense = 1;
        industryCost = 4;
    }
}
