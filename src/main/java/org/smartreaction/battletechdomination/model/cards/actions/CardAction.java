package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

public class CardAction implements Action {
    private Card card;

    public CardAction(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
