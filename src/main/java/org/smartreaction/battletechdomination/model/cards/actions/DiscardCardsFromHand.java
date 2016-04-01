package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.List;

public class DiscardCardsFromHand extends Action {
    private int numCardsToDiscard;

    private List<Card> cards;

    public DiscardCardsFromHand(int numCardsToDiscard, String text) {
        this.numCardsToDiscard = numCardsToDiscard;
        this.text = text;
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
