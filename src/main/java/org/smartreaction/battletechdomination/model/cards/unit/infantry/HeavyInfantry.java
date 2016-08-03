package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Inspiring;

public class HeavyInfantry extends InfantryUnit {
    public HeavyInfantry() {
        name = "Heavy Infantry";
        cardText = "INSPIRING: +1 Attack per Infantry Platoon in your deployment zone.";
        attack = 1;
        defense = 1;
        industryCost = 3;
        imageFile = "HeavyInfantry.png";

        addAbility(new Inspiring(this));
    }
}
