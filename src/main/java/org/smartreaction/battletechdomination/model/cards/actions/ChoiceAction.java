package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.Arrays;
import java.util.List;

public class ChoiceAction implements Action {
    private Card card;

    private List<Choice> choices;

    private String abilityName;

    public ChoiceAction(Card card, Choice... choices) {
        this.card = card;
        this.choices = Arrays.asList(choices);
    }

    public Card getCard() {
        return card;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
