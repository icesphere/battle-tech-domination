package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Aerospace extends UnitAbility implements OverrunOpponentAbility, OverrunByOpponentAbility {
    //AEROSPACE: Damage this unit if you are Overrun or if you Overrun your opponent.

    public Aerospace(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + "'s " + unit.getName() + " damaged due to Aerospace ability.");
        player.cardDamaged(unit);
    }
}
