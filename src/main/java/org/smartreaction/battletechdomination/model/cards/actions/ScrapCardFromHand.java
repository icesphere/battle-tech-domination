package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.CardType;

public class ScrapCardFromHand extends Action {
    private CardType cardType;

    public ScrapCardFromHand(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }
}
