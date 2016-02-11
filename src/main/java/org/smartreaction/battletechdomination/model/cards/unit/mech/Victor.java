package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Victor extends MechUnit {
    public Victor() {
        name = "Victor";
        subName = "VTR-9S";
        cardText = "JUMP JETS: When you deploy this unit, your opponent must damage a unit costing 6 Industry or more.";
        attack = 2;
        defense = 2;
        industryCost = 6;
    }

    //todo
}
