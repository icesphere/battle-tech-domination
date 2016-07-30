package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class UnitFromHandToTopOfDeck extends Action {
    public UnitFromHandToTopOfDeck(String text) {
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) && card.isUnit();
    }

    @Override
    public boolean processAction(Player player) {
        if (player.numUnitsInHand() == 0) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is moving a Unit from their hand to the top of their deck");
            return true;
        }
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        player.getHand().remove(result.getSelectedCard());
        player.addGameLog(player.getPlayerName() + " put " + result.getSelectedCard().getName() + " from hand on top of deck");
        player.addCardToTopOfDeck(result.getSelectedCard());
        return true;
    }
}
