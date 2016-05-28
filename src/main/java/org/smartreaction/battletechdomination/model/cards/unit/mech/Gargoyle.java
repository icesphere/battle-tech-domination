package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Gargoyle extends MechUnit {
    public Gargoyle() {
        name = "Gargoyle";
        subName = "MAN-O'-WAR";
        cardText = "FAST ASSAULT: This unit cannot be damaged unless your opponent has a unit costing 6 Industry or more in his deployment zone.";
        attack = 3;
        defense = 2;
        industryCost = 5;
        losTechCost = 1;

        //todo
    }
}
