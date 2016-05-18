package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.SwarmAttack;
import org.smartreaction.battletechdomination.model.players.Player;

public class BattleArmorSquad extends InfantryUnit {
    SwarmAttack swarmAttack;

    public BattleArmorSquad() {
        name = "Battle Armor Squad";
        cardText = "SWARM ATTACK: When you deploy this unit, your opponent must damage a Mech.";
        attack = 0;
        defense = 1;
        industryCost = 4;

        swarmAttack = new SwarmAttack(this);
    }

    @Override
    public void unitDeployed(Player player) {
        swarmAttack.useAbility(player);
    }
}
