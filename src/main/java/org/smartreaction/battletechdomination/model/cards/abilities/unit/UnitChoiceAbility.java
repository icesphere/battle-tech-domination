package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.players.Player;

public interface UnitChoiceAbility {
    void abilityChoiceMade(Player player, int choice);
}
