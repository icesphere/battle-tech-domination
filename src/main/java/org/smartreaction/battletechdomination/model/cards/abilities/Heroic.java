package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Heroic extends Ability implements CombatPhaseBonusAbility {
    //HEROIC: If you have fewer Units in your deployment zone than your opponent has in his, +1 Attack and +2 Defense.

    public Heroic(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getNumUnitsInDeploymentZone() < player.getOpponent().getNumUnitsInDeploymentZone()) {
            player.addGameLog(player.getPlayerName() + " gained +1 Attack and +2 Defense from Heroic ability on " + unit.getName());
            player.addAttack(1);
            player.addDefense(2);
        }
    }
}
