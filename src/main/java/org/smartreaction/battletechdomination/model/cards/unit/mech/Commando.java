package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Scout;

public class Commando extends MechUnit {
    public Commando() {
        name = "Commando";
        subName = "COM-2D";
        cardText = "SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.";
        attack = 1;
        defense = 0;
        industryCost = 2;
        imageFile = "Commando3.png";

        addAbility(new Scout(this));
    }
}
