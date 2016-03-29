package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.List;

public class CardsOnTopOfDeckInAnyOrder implements Action {
    private List<Card> cards;

    public CardsOnTopOfDeckInAnyOrder(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
