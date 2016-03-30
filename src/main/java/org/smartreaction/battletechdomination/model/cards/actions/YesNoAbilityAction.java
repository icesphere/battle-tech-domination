package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;

public class YesNoAbilityAction implements Action {
    private Card card;
    private String abilityName;
    private String text;

    public YesNoAbilityAction(Card card, String abilityName, String text) {
        this.card = card;
        this.abilityName = abilityName;
        this.text = text;
    }

    public Card getCard() {
        return card;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getText() {
        return text;
    }
}
