package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SwarmAttack;

public class EliteElementals extends InfantryUnit {
    public EliteElementals() {
        name = "Elite Elementals";
        cardText = "SWARM ATTACK: When you deploy this unit, your opponent must damage a Mech.";
        attack = 0;
        defense = 1;
        industryCost = 4;
        imageFile = "EliteElements.png";

        addAbility(new SwarmAttack(this));
    }
}
