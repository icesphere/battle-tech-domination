package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class VeryFast extends UnitAbility implements UnitBuyAbility {
    //VERY FAST: When you buy this, immediately place it in your deployment zone.

    public VeryFast(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " used Very Fast ability to place " + unit.getName() + " in deployment zone");
        player.deployUnit(unit);
    }
}
