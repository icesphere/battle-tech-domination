package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Sniper;

public class Griffin extends MechUnit {
    public Griffin() {
        name = "Griffin";
        subName = "GRF-3M";
        cardText = "SNIPER: When you deploy this unit, your opponent must damage a unit.";
        attack = 1;
        defense = 1;
        industryCost = 4;

        addAbility(new Sniper(this));
    }
}
