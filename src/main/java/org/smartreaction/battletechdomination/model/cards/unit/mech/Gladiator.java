package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Gladiator extends MechUnit {
    public Gladiator() {
        name = "Gladiator";
        subName = "EXECUTIONER";
        cardText = "TOTEM MECH: This unit cannot be damaged if it is the only Mech in your deployment zone.";
        attack = 4;
        defense = 3;
        industryCost = 7;
        losTechCost = 1;
    }

    //todo
}
