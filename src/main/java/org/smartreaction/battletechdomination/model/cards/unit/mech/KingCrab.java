package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.DualAC20s;

public class KingCrab extends MechUnit {
    public KingCrab() {
        name = "King Crab";
        subName = "KGC-000";
        cardText = "DUAL AC/20s: When you deploy this unit, your opponent must damage a Mech.";
        attack = 3;
        defense = 4;
        industryCost = 8;
        imageFile = "KingCrab.png";

        addAbility(new DualAC20s(this));
    }
}
