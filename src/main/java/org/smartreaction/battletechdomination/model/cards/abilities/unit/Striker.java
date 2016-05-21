package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Striker extends UnitAbility {
    //STRIKER: At the start of your Combat phase, your opponent reveals and discards the top card of his deck. If it is a Resource card, +1 Card.

    public Striker(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.getOpponent().revealTopCardOfDeck();
        if (card != null && card.isResource()) {
            player.getOpponent().removeCardFromDeck(card);
            player.getOpponent().addCardToDiscard(card);
            player.drawCards(1);
            player.addGameLog(player.getPlayerName() + " got to draw 1 Card from Striker ability on " + unit.getName());
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
