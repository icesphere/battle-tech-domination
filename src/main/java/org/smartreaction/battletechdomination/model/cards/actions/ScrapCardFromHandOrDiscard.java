package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.CardType;

public class ScrapCardFromHandOrDiscard extends Action {
    private CardType cardType;
    private boolean optional;

    public ScrapCardFromHandOrDiscard(CardType cardType, boolean optional, String text) {
        this.cardType = cardType;
        this.optional = optional;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean isOptional() {
        return optional;
    }
}
