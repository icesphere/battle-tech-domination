package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class BlackMarket extends Support implements SupportCardAction {
    public BlackMarket() {
        name = "Black Market";
        cardText = "Scrap a Resource card from your hand. If you do, +3 Industry.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Resource card from your hand to gain +3 Industry."));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) && card.isResource();
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().stream().anyMatch(Card::isResource)) {
            player.addGameLog(player.getPlayerName() + " is scrapping a Resource card from hand to gain +3 Industry");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        player.addGameLog(player.getPlayerName() + " scrapped a Resource from their hand to gain +3 Industry.");
        player.scrapCardFromHand(result.getSelectedCard());
        player.addIndustry(3);
    }
}
