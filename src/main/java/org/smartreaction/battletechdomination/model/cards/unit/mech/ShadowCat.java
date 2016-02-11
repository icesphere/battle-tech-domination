package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class ShadowCat extends MechUnit {
    public ShadowCat() {
        name = "Shadow Cat";
        cardText = "JUMP JETS: When you deploy this unit, your opponent must damage a unit costing 6 Industry or more";
        attack = 3;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;
    }

    //todo
}
