package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Expendable;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Mobile;

public class MechanizedInfantry extends InfantryUnit {
    public MechanizedInfantry() {
        name = "Mechanized Infantry";
        cardText = "MOBILE: +1 Action when you deploy this unit. EXPENDABLE: You may damage this unit at the start of your Combat phase.  If you do, +1 Attack.";
        attack = 0;
        defense = 1;
        industryCost = 2;
        imageFile = "MechanizedInfantry.png";

        addAbility(new Expendable(this));
        addAbility(new Mobile(this));
    }
}
