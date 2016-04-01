package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.CardType;

public class DamageUnit extends Action {
    private CardType cardType;

    public DamageUnit() {
        this.text = "Damage a Unit";
    }

    public DamageUnit(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }
}
