package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Arrays;
import java.util.List;

public class ChoiceAction extends Action {
    private Card card;

    private List<Choice> choices;

    private String abilityName;

    public ChoiceAction(Card card, String text, Choice... choices) {
        this.card = card;
        this.choices = Arrays.asList(choices);
        this.text = text;
    }

    @Override
    public List<Choice> getChoices() {
        return choices;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return false;
    }

    @Override
    public boolean processAction(Player player) {
        return true;
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        if (abilityName != null) {
            player.abilityChoiceMade(card, abilityName, result.getChoiceSelected());
        } else {
            card.choiceMade(result.getChoiceSelected(), player);
        }
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
