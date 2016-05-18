package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Expendable extends Ability {
    //EXPENDABLE: You may damage this unit at the start of your Combat phase. If you do, +1 Attack.

    public Expendable(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged((Unit) card);
        player.addGameLog(player.getPlayerName() + " gets +1 Attack from damaging Expendable unit: " + card.getName());
        player.addAttack(1);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
