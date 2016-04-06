package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class YesNoAbilityAction extends Action {
    private Card card;
    private String abilityName;

    public YesNoAbilityAction(Card card, String abilityName, String text) {
        this.card = card;
        this.abilityName = abilityName;
        this.text = text;
    }

    @Override
    public List<Choice> getChoices() {
        List<Choice> choices = new ArrayList<>();

        Choice yes = new Choice(1, "Yes");
        Choice no = new Choice(2, "No");

        choices.add(yes);
        choices.add(no);

        return choices;
    }

    public Card getCard() {
        return card;
    }

    public String getAbilityName() {
        return abilityName;
    }
}
