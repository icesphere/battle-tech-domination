package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.abilities.Expendable;
import org.smartreaction.battletechdomination.model.cards.InfantryUnit;

public class InfantryPlatoon extends InfantryUnit implements Expendable {
    public InfantryPlatoon() {
        name = "Infantry Platoon";
        cardText = "EXPENDABLE: You may damage this unit at the start of your Combat phase.  If you do, +1 Attack.";
        attack = 0;
        defense = 1;
        industryCost = 0;
    }
}
