package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class LongRangeBarrage extends UnitAbility {
    //LONG RANGE BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a... Resource card, your opponent must discard a card; Unit card, your opponent must damage a unit; Otherwise, no effect.

    public LongRangeBarrage(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card != null) {
            if (card.isResource()) {
                player.addGameLog(player.getOpponent().getPlayerName() + " must discard a card due to Long Range Barrage ability on " + unit.getName());
                player.addOpponentAction(new DiscardCardsFromHand(1, "Discard a card"));
            } else if (card.isUnit()) {
                player.addGameLog(player.getOpponent().getPlayerName() + " must damage a unit due to Long Range Barrage ability on " + unit.getName());
                player.addOpponentAction(new DamageUnit());
            }
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
