package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.AntiInfantry;

public class Elementals extends InfantryUnit {
    public Elementals() {
        name = "Elementals";
        cardText = "ANTI-INFANTRY: When you deploy this unit, your opponent must damage an Infantry unit.";
        attack = 2;
        defense = 0;
        industryCost = 4;
        imageFile = "Elementals.png";

        addAbility(new AntiInfantry(this));
    }
}
