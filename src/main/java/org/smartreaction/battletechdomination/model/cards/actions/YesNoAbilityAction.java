package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class YesNoAbilityAction extends Action {
    private SupportActionChoice card;

    public YesNoAbilityAction(SupportActionChoice card, String text) {
        this.card = card;
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
