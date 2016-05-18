package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Expendable;

public class InfantryPlatoon extends InfantryUnit implements BaseSupply {
    public InfantryPlatoon() {
        name = "Infantry Platoon";
        cardText = "EXPENDABLE: You may damage this unit at the start of your Combat phase. If you do, +1 Attack.";
        attack = 0;
        defense = 1;
        industryCost = 0;

        addAbility(new Expendable(this));
    }
}
