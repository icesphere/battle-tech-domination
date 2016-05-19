package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.ActiveProbe;

public class Fenris extends MechUnit implements ActiveProbe {
    public Fenris() {
        name = "Fenris";
        subName = "ICE FERRET";
        cardText = "VERY FAST: When you buy this, you may immediately place it in your deployment zone.  ACTIVE PROBE: +1 Card when your opponent deploys a Mech or Vehicle unit.";
        attack = 2;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;
    }

    //todo
}
