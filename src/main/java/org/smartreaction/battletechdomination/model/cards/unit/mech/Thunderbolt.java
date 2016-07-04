package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.LowMaintenance;

public class Thunderbolt extends MechUnit {
    public Thunderbolt() {
        name = "Thunderbolt";
        subName = "TDR-5S";
        cardText = "LOW MAINTENANCE: +1 Card when you deploy this unit.";
        attack = 2;
        defense = 1;
        industryCost = 5;
        imageFile = "Thunderbolt.png";

        addAbility(new LowMaintenance(this));
    }
}
