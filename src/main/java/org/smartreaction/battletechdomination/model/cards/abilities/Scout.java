package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class Scout extends Ability {
    //SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.

    public Scout(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.getOpponent().revealTopCardOfDeck();

        Choice choice1 = new Choice(1, "Opponent discards " + card.getName());
        Choice choice2 = new Choice(2, "Opponent puts " + card.getName() + " back on top of deck");

        player.makeAbilityChoice(card, "Scout", "Opponent's top card of deck is " + card.getName() + ". Do you want to discard it or put it back?", choice1, choice2);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
