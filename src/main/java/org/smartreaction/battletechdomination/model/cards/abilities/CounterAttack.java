package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class CounterAttack extends Ability implements UnitDamagedAbility {
    //COUNTER ATTACK: When this unit is damaged, your opponent must damage a unit with the highest Industry cost in his deployment zone.

    public CounterAttack(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getOpponent().getNumMechUnitsInDeploymentZone() > 0) {
            player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
            player.addGameLog(player.getOpponent().getPlayerName() + " must damage a Mech due to " + player.getPlayerName() + "'s Counter Attack ability on " + unit.getName());
        }
    }
}
