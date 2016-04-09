package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.players.Player;

public class ScrapCardFromHand extends Action {
    private CardType cardType;

    public ScrapCardFromHand(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) && (cardType == null || cardType == card.getCardType());
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getHand().isEmpty() || (cardType != null && player.getHand().stream().noneMatch(c -> c.getCardType() == cardType))) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is scrapping a card from their hand");
            return true;
        }
    }
}
