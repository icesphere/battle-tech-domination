package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Expendable extends Ability {
    //EXPENDABLE: You may damage this unit at the start of your Combat phase. If you do, +1 Attack.

    public Expendable(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged(unit);
        player.addGameLog(player.getPlayerName() + " gets +1 Attack from damaging Expendable unit: " + unit.getName());
        player.addAttack(1);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
