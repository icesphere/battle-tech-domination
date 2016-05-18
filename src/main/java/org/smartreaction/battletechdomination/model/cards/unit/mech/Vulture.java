package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.LRMFireSupport;
import org.smartreaction.battletechdomination.model.players.Player;

public class Vulture extends MechUnit {
    LRMFireSupport lrmFireSupport;

    public Vulture() {
        name = "Vulture";
        subName = "MAD DOG";
        cardText = "LRM FIRE SUPPORT: Each other Mech in your deployment zone gets +1 Attack.";
        attack = 2;
        defense = 2;
        industryCost = 5;
        losTechCost = 1;

        lrmFireSupport = new LRMFireSupport(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        lrmFireSupport.useAbility(player);
    }
}
