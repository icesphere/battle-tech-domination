package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Overheat extends UnitAbility {
    //OVERHEAT: You may damage this unit at the start of your combat phase. If you do, +4 Attack.

    public Overheat(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged(unit);
        player.addAttack(4);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
