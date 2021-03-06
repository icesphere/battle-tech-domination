package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Mobile extends UnitAbility implements UnitDeployedAbility {
    //MOBILE: +1 Action when you deploy this unit.

    public Mobile(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addActions(1);
    }
}
