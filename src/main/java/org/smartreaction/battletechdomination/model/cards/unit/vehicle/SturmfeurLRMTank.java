package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.LRMFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.Tank;
import org.smartreaction.battletechdomination.model.players.Player;

public class SturmfeurLRMTank extends VehicleUnit implements Tank {
    LRMFireSupport lrmFireSupport;

    public SturmfeurLRMTank() {
        name = "Sturmfeur LRM Tank";
        cardText = "LRM FIRE SUPPORT: Each Mech in your deployment zone gets +1 Attack. TANK: If this unit is damaged, scrap it instead.";
        attack = 1;
        defense = 0;
        industryCost = 2;

        lrmFireSupport = new LRMFireSupport(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        lrmFireSupport.useAbility(player);
    }
}
