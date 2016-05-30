package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class Refinery extends Support implements SupportCardAction {
    public Refinery() {
        name = "Refinery";
        cardText = "Scrap a Resource card from your hand. Gain a Resource card costing up to 3 Industry more than the card you scrapped. Put the gained card into your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Resource card from your hand. Gain a Resource card costing up to 3 Industry more than the card you scrapped. The gained card will be put into your hand."));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return card.isResource() && cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is gaining a card from Refinery");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        player.scrapCardFromHand(result.getSelectedCard());
        player.gainFreeResourceCardIntoHand(result.getSelectedCard().getIndustryCost() + 3);
    }
}
