package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.CardType;

public class DamageUnit implements Action {
    private CardType cardType;

    public DamageUnit() {
    }

    public DamageUnit(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }
}
