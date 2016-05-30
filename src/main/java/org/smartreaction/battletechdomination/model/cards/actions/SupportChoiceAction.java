package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Arrays;
import java.util.List;

public class SupportChoiceAction extends Action {
    private SupportActionChoice card;

    private List<Choice> choices;

    public SupportChoiceAction(SupportActionChoice card, String text, Choice... choices) {
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
        card.supportActionChoiceMade(player, result.getChoiceSelected());
    }
}
