package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Inspiring;

public class VeteranInfantry extends InfantryUnit implements Inspiring {
    public VeteranInfantry() {
        name = "Veteran Infantry";
        cardText = "INSPIRING: +1 Attack per Infantry Platoon in your deployment zone.";
        attack = 0;
        defense = 1;
        industryCost = 2;
    }
}
