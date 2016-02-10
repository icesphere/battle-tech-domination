package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Commando extends MechUnit {
    public Commando() {
        name = "Commando";
        subName = "COM-2D";
        cardText = "DURABLE: +1 Card when this unit is damaged. SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.";
        attack = 0;
        defense = 1;
        industryCost = 2;
    }

    //todo
}
