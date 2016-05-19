package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Tank extends UnitAbility {
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
