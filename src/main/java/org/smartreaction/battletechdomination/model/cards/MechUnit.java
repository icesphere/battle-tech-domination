package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.abilities.AC20;

public abstract class MechUnit extends Unit {
    protected MechUnit() {
        cardType = CardType.UNIT_MECH;
    }

    protected boolean abilityUsed;

    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    public boolean isAbilityAvailable(Player player) {
        if (abilityUsed) {
            return false;
        }

        if (this instanceof AC20) {
            if (player.getTurnPhase() == TurnPhase.ACTION) {
                return true;
            }
        }

        return false;
    }
}
