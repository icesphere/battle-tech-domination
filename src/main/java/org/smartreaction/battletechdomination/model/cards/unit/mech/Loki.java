package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Loki extends MechUnit {
    public Loki() {
        name = "Loki";
        subName = "HELLBRINGER";
        cardText = "ECM: Each other Mech in your deployment zone gets +1 Defense. ACTIVE PROBE: +1 Card when your opponent deploys a Mech or Vehicle unit.";
        attack = 3;
        defense = 2;
        industryCost = 6;
        losTechCost = 1;
    }

    //todo
}
