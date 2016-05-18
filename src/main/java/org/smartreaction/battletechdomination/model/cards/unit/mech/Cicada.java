package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Mobile;
import org.smartreaction.battletechdomination.model.players.Player;

public class Cicada extends MechUnit {
    Mobile mobile;

    public Cicada() {
        name = "Cicada";
        subName = "CDA-2A";
        cardText = "MOBILE: +1 Action when you deploy this unit.";
        attack = 1;
        defense = 1;
        industryCost = 4;

        mobile = new Mobile(this);
    }

    @Override
    public void unitDeployed(Player player) {
        mobile.useAbility(player);
    }
}
