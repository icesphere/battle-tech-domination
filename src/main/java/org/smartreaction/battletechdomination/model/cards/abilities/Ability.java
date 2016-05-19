package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Ability {
    protected Unit unit;

    public Ability(Unit unit) {
        this.unit = unit;
    }

    public abstract void useAbility(Player player);

    public boolean isAbilityAvailable(Player player) {
        return false;
    }

    public Unit getUnit() {
        return unit;
    }
}
