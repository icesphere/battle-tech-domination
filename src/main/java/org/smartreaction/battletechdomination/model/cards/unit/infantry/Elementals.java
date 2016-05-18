package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.AntiInfantry;

public class Elementals extends InfantryUnit {
    public Elementals() {
        name = "Elementals";
        cardText = "ANTI-INFANTRY: When you deploy this unit, your opponent must damage an Infantry unit.";
        attack = 1;
        defense = 1;
        industryCost = 4;

        addAbility(new AntiInfantry(this));
    }
}
