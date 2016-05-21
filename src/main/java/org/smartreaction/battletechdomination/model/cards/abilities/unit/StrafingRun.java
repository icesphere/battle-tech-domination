package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class StrafingRun extends UnitAbility {
    //STRAFING RUN: You may damage this unit at the start of your Combat phase. If you do, your opponent must damage a Mech.

    public StrafingRun(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getOpponent().getPlayerName() + " must damage a Mech due to Strafing Run ability on " + unit.getName());
        player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech"));
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
