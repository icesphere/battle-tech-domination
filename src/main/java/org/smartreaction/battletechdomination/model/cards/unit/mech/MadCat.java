package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.FastAssault;
import org.smartreaction.battletechdomination.model.players.Player;

public class MadCat extends MechUnit {
    FastAssault fastAssault;

    public MadCat() {
        name = "Mad Cat";
        subName = "TIMBERWOLF";
        cardText = "FAST ASSAULT: When you deploy this unit, your opponent damages a unit costing 5 Industry or less of your choice.";
        attack = 4;
        defense = 3;
        industryCost = 7;
        losTechCost = 1;
        imageFile = "madcat_v2.jpeg";

        fastAssault = new FastAssault(this);
    }

    @Override
    public void unitDeployed(Player player) {
        fastAssault.useAbility(player);
    }
}
