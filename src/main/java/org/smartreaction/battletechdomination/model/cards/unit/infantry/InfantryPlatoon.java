package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Expendable;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SoftTarget;

public class InfantryPlatoon extends InfantryUnit implements BaseSupply, SoftTarget {
    public InfantryPlatoon() {
        name = "Infantry Platoon";
        cardText = "EXPENDABLE: You may damage this unit at the start of your Combat phase. If you do, +1 Attack. SOFT TARGET: If this unit is damaged while your opponent has a Mech in their deployment zone, scrap this instead.";
        attack = 0;
        defense = 1;
        industryCost = 0;
        imageFile = "infantry_platoon.jpg";

        addAbility(new Expendable(this));
    }
}
