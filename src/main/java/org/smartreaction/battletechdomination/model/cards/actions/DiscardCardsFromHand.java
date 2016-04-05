package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class DiscardCardsFromHand extends Action {
    private int numCardsToDiscard;

    private List<Card> selectedCards = new ArrayList<>(3);

    public DiscardCardsFromHand(int numCardsToDiscard, String text) {
        this.numCardsToDiscard = numCardsToDiscard;
        this.text = text;
    }

    public int getNumCardsToDiscard() {
        return numCardsToDiscard;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }
}
