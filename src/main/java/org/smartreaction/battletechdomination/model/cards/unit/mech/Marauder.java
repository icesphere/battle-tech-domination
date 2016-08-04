package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Renowned;

public class Marauder extends MechUnit {
    public Marauder() {
        name = "Marauder";
        subName = "MAD-3R";
        cardText = "RENOWNED: +1 Card when you deploy this unit.";
        attack = 3;
        defense = 3;
        industryCost = 7;
        imageFile = "Marauder2.png";

        addAbility(new Renowned(this));
    }
}
