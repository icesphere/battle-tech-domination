package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.CardType;

public class ScrapCardFromHandOrDiscard implements Action {
    private CardType cardType;
    private boolean optional;

    public ScrapCardFromHandOrDiscard(CardType cardType, boolean optional) {
        this.cardType = cardType;
        this.optional = optional;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean isOptional() {
        return optional;
    }
}
