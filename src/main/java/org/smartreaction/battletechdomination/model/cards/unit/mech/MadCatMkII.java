package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class MadCatMkII extends MechUnit {
    public MadCatMkII() {
        name = "Mad Cat Mk II";
        cardText = "CHAMPION MECH: This unit can only be deployed if you have fewer Mechs in your deployment zone than your opponent has in his.";
        attack = 6;
        defense = 4;
        industryCost = 9;
        losTechCost = 1;
    }

    //todo
}
