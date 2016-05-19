package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SwarmAttack;

public class BattleArmorSquad extends InfantryUnit {
    public BattleArmorSquad() {
        name = "Battle Armor Squad";
        cardText = "SWARM ATTACK: When you deploy this unit, your opponent must damage a Mech.";
        attack = 0;
        defense = 1;
        industryCost = 4;

        addAbility(new SwarmAttack(this));
    }
}
