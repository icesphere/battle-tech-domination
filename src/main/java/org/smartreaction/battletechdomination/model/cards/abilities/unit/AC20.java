package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class AC20 extends UnitAbility implements UnitDeployedAbility {
    //AC/20: Once per turn during your action phase, you may reveal the top card of your deck. If it is a... Resource card, your opponent must damage a Mech; Support card, damage this unit; Unit card, no effect.

    public AC20(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card instanceof Resource) {
            player.addGameLog(player.getOpponent().getPlayerName() + " must damage a Mech unit due to AC/20 ability on " + card.getName());
            player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
        } else if (card instanceof Support) {
            player.cardDamaged(unit);
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
