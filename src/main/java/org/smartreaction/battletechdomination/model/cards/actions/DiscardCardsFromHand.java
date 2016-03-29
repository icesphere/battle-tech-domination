package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.List;

public class DiscardCardsFromHand implements Action {
    private int numCardsToDiscard;

    private List<Card> cards;

    public DiscardCardsFromHand(int numCardsToDiscard) {
        this.numCardsToDiscard = numCardsToDiscard;
    }

    public int getNumCardsToDiscard() {
        return numCardsToDiscard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
