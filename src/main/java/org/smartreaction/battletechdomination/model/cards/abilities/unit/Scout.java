package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Scout extends UnitAbility implements UnitChoiceAbility {
    //SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.

    public Scout(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.getOpponent().revealTopCardOfDeck();

        if (card != null) {
            Choice choice1 = new Choice(1, "Opponent discards " + card.getName());
            Choice choice2 = new Choice(2, "Opponent puts " + card.getName() + " back on top of deck");

            player.makeUnitAbilityChoice(this, "Opponent's top card of deck is " + card.getName() + ". Do you want to discard it or put it back?", choice1, choice2);
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog("Chose to discard top card of opponent's deck");
            player.getOpponent().discardTopCardOfDeck();
        } else {
            player.addGameLog("Chose to keep top card of opponent's deck on top of deck");
        }
    }
}
