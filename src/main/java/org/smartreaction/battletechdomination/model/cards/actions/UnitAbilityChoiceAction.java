package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.UnitChoiceAbility;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Arrays;
import java.util.List;

public class UnitAbilityChoiceAction extends Action {
    private UnitChoiceAbility ability;

    private List<Choice> choices;

    public UnitAbilityChoiceAction(UnitChoiceAbility ability, String text, Choice... choices) {
        this.ability = ability;
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
    public boolean processActionResult(Player player, ActionResult result) {
        ability.abilityChoiceMade(player, result.getChoiceSelected());
        return true;
    }
}
