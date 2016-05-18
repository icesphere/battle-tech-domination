package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Flamers;
import org.smartreaction.battletechdomination.model.players.Player;

public class Firestarter extends MechUnit {
    Flamers flamers;

    public Firestarter() {
        name = "Firestarter";
        subName = "FS9-H";
        cardText = "FLAMERS: When you deploy this unit, your opponent must damage all Infantry Platoons in his deployment zone.";
        attack = 1;
        defense = 0;
        industryCost = 3;

        flamers = new Flamers(this);
    }

    @Override
    public void unitDeployed(Player player) {
        flamers.useAbility(player);
    }
}
