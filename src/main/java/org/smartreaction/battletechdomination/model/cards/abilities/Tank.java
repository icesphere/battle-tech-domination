package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Tank extends Ability {
    //TANK: If this unit is damaged, scrap it instead.

    public Tank(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(unit.getName() + " was scrapped due to Tank ability");
        player.scrapUnitFromDeploymentZone(unit);
    }
}
