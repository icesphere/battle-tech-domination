package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.ActiveProbe;
import org.smartreaction.battletechdomination.model.cards.abilities.ECM;

public class Raven extends MechUnit implements ECM, ActiveProbe {
    public Raven() {
        name = "Raven";
        subName = "RVN-3L";
        cardText = "ECM: Each other Mech in your deployment zone gets +1 Defense. ACTIVE PROBE: +1 Card when your opponent deploys a Mech or Vehicle unit.";
        attack = 0;
        defense = 0;
        industryCost = 3;
    }
}
