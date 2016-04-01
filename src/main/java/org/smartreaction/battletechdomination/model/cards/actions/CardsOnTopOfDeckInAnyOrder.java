package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.List;

public class CardsOnTopOfDeckInAnyOrder extends Action {
    private List<Card> cards;

    public CardsOnTopOfDeckInAnyOrder(List<Card> cards, String text) {
        this.cards = cards;
        this.text = text;
    }

    public List<Card> getCards() {
        return cards;
    }
}
