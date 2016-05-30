package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class RaidedSupplies extends OverrunSupport implements SupportCardAction {
    public RaidedSupplies() {
        name = "Raided Supplies";
        cardText = "You may discard 2 cards from your hand.  If you do, return this card to the Overrun pile.";
        overrunAmount = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Discard 2 cards from your hand to return Raided Supplies to Overrun pile"));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
            if (!cardAction.getSelectedCards().contains(card)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().size() < 2) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is discarding 2 cards to return Raided Supplies to Overrun pile");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        result.getSelectedCards().stream().forEach(player::discardCardFromHand);
        player.getCardsPlayed().remove(this);
    }
}
