package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Versatile;

public class Quickdraw extends MechUnit {
    public Quickdraw() {
        name = "Quickdraw";
        subName = "QKD-4G";
        cardText = "VERSATILE: When you deploy this unit, choose one: +1 Card; +1 Action; +1 Industry";
        attack = 2;
        defense = 1;
        industryCost = 5;
        imageFile = "Quickdraw.png";

        addAbility(new Versatile(this));
    }
}
