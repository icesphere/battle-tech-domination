package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.Ability;
import org.smartreaction.battletechdomination.model.players.Player;

public abstract class UnitAbility extends Ability {
    protected Unit unit;

    public UnitAbility(Unit unit) {
        this.unit = unit;
    }

    public boolean isAbilityAvailable(Player player) {
        return false;
    }

    public Unit getUnit() {
        return unit;
    }
}
