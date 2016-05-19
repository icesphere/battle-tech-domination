package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.UnitChoiceAbility;

public class YesNoUnitAbilityChoiceAction extends UnitAbilityChoiceAction {
    public YesNoUnitAbilityChoiceAction(UnitChoiceAbility ability, String text) {
        super(ability, text, new Choice(1, "Yes"), new Choice(2, "No"));
    }
}
