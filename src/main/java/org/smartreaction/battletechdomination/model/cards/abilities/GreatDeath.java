package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class GreatDeath extends Ability implements OverrunOpponentAbility {
    //"GREAT DEATH": When you overrun your opponent, he must damage an additional unit at the end of your Combat phase.

    public GreatDeath(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getOpponent().getPlayerName() + " must damage a unit due to Great Death ability on " + unit.getName());
        player.addOpponentAction(new DamageUnit());
    }
}
