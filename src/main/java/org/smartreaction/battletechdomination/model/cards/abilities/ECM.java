package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class ECM extends Ability implements CombatPhaseBonusAbility {
    //ECM: Each other Mech in your deployment zone gets +1 Defense.

    public ECM(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (!player.isYourTurn()) {
            List<Unit> otherDeploymentZoneCards = new ArrayList<>(player.getDeploymentZone());
            otherDeploymentZoneCards.remove(unit);
            for (Unit otherDeploymentZoneCard : otherDeploymentZoneCards) {
                if (otherDeploymentZoneCard instanceof MechUnit) {
                    player.addGameLog(player.getPlayerName() + "'s " + otherDeploymentZoneCard.getName() + " gained +1 Defense from ECM ability on " + unit.getName());
                    otherDeploymentZoneCard.setBonusDefense(otherDeploymentZoneCard.getBonusDefense() + 1);
                }
            }
        }
    }
}
