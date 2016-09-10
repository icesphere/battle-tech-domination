package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class AC20 extends UnitAbility {
    //AC/20: At the start of your Combat phase, you may reveal the top card of your deck. If it is a Unit or Support card, your opponent must damage a Unit.

    public AC20(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card instanceof Unit || card instanceof Support) {
            player.addGameLog(player.getOpponent().getPlayerName() + " must damage a Unit due to AC/20 ability on " + card.getName());
            player.addOpponentAction(new DamageUnit());
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
