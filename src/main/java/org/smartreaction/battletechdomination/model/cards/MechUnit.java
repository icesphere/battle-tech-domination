package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.abilities.*;

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

        TurnPhase turnPhase = player.getTurnPhase();

        if (this instanceof AC20) {
            if (turnPhase == TurnPhase.ACTION) {
                return true;
            }
        }

        if (this instanceof DeathFromAbove) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof Expendable) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof HeavyFireSupport) {
            if (turnPhase == TurnPhase.ACTION) {
                return true;
            }
        }

        if (this instanceof MobileFireSupport) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof Overheat) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof QuadERPPCs) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        if (this instanceof Scout) {
            if (turnPhase == TurnPhase.COMBAT_START) {
                return true;
            }
        }

        return false;
    }
}
