package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Mobile;
import org.smartreaction.battletechdomination.model.players.Player;

public class Locust extends MechUnit {
    Mobile mobile;

    public Locust() {
        name = "Locust";
        subName = "LCT-1V";
        cardText = "MOBILE: +1 Action when you deploy this unit. SPOTTER: When your opponent gains an Overrun card, he must place it on top of his deck.";
        attack = 0;
        defense = 0;
        industryCost = 0;

        mobile = new Mobile(this);
    }

    @Override
    public void unitDeployed(Player player) {
        mobile.useAbility(player);
    }

    //todo
}
