package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.ReconInForce;

public class ShadowHawk extends MechUnit implements ReconInForce {
    public ShadowHawk() {
        name = "Shadow Hawk";
        subName = "SHD-2H";
        cardText = "RECON-IN-FORCE: When you deploy this unit, you may discard a card. If you do, your opponent must gain a Raided Supplies card.";
        attack = 1;
        defense = 1;
        industryCost = 4;
    }
}
