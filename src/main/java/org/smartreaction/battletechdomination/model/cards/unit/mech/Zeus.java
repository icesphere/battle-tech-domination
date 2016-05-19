package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Durable;

public class Zeus extends MechUnit {
    public Zeus() {
        name = "Zeus";
        subName = "ZEU-9S";
        cardText = "DURABLE: +1 Card when this unit is damaged.";
        attack = 2;
        defense = 3;
        industryCost = 6;

        addAbility(new Durable(this));
    }
}
