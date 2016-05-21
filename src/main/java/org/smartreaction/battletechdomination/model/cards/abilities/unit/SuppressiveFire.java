package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class SuppressiveFire extends UnitAbility implements CombatPhaseBonusAbility {
    //SUPPRESSIVE FIRE: +1 Attack if you have more Mechs in your deployment zone than your opponent has in his.

    public SuppressiveFire(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getNumMechUnitsInDeploymentZone() > player.getOpponent().getNumMechUnitsInDeploymentZone()) {
            player.addGameLog(player.getPlayerName() + " gained +1 Attack from Suppressive Fire ability on " + unit.getName());
            player.addAttack(1);
        }
    }
}
