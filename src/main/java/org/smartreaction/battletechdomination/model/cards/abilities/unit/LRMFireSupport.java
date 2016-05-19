package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class LRMFireSupport extends UnitAbility implements CombatPhaseBonusAbility {
    //LRM FIRE SUPPORT: Each Mech in your deployment zone gets +1 Attack.

    public LRMFireSupport(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.isYourTurn()) {
            List<Unit> otherDeploymentZoneCards = new ArrayList<>(player.getDeploymentZone());
            otherDeploymentZoneCards.remove(unit);
            for (Unit deploymentZoneCard : otherDeploymentZoneCards) {
                if (deploymentZoneCard instanceof MechUnit) {
                    player.addGameLog(player.getPlayerName() + "'s " + deploymentZoneCard.getName() + " gained +1 Attack from LRMFireSupport ability on " + unit.getName());
                    deploymentZoneCard.setBonusAttack(deploymentZoneCard.getBonusAttack() + 1);
                }
            }
        }
    }
}
