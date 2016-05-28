package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class MobileHeavy extends UnitAbility implements CombatPhaseBonusAbility {
    //MOBILE HEAVY: +2 Attack if your opponent does not have a Mech costing 5 Industry or more in his deployment zone.

    public MobileHeavy(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getOpponent().getDeploymentZone().stream().noneMatch(u -> u.isMechUnit() && u.getIndustryCost() >= 5)) {
            unit.setBonusAttack(unit.getBonusAttack() + 2);
            player.addGameLog(player.getPlayerName() + " gained +2 Attack from Mobile Heavy ability on " + unit.getName());
        }
    }
}
