package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class AC10 extends UnitAbility implements CombatPhaseBonusAbility {
    //AC/10: +1 Attack if your opponent has a Mech or Vehicle in his deployment zone.

    public AC10(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.isYourTurn() && player.getOpponent().getNumMechUnitsInDeploymentZone() > 0 || player.getOpponent().getNumVehicleUnitsInDeploymentZone() > 0) {
            player.addGameLog(player.getPlayerName() + " gained +1 Attack from AC/10 ability on " + unit.getName());
            player.addAttack(1);
        }
    }
}
