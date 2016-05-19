package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Durable extends UnitAbility implements UnitDamagedAbility {
    public Durable(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " gets to draw 1 card due to Durable ability on " + unit.getName());
        player.drawCards(1);
    }
}
