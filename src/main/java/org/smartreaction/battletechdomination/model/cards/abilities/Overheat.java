package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Overheat extends Ability {
    //OVERHEAT: You may damage this unit at the start of your combat phase. If you do, +4 Attack.

    public Overheat(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged((Unit) card);
        player.addAttack(4);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
