package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class HiddenRoute extends Support implements SupportCardAction {
    public HiddenRoute() {
        name = "Hidden Route";
        cardText = "+1 Card. +1 Action. Set aside a card from your hand face down. At the start of your next turn, put the card into your hand.";
        industryCost = 2;
        imageFile = "HiddenRoute.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(1);
        player.addAction(new CardAction(this, "Set aside a card from your hand face down. At the start of your next turn the card will be put into your hand."));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is setting aside a card for Hidden Route");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        player.getHand().remove(result.getSelectedCard());
        player.getHiddenBaseCards().add(result.getSelectedCard());
    }
}
