package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.MobileHeavy;

public class Dragon extends MechUnit {
    public Dragon() {
        name = "Dragon";
        subName = "DRG-5N";
        cardText = "MOBILE HEAVY: +2 Attack if your opponent does not have a Mech costing 5 Industry or more in his deployment zone.";
        attack = 1;
        defense = 2;
        industryCost = 5;

        addAbility(new MobileHeavy(this));
    }
}
