package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.TotemMech;

public class Gladiator extends MechUnit implements TotemMech {
    public Gladiator() {
        name = "Gladiator";
        subName = "EXECUTIONER";
        cardText = "TOTEM MECH: This unit cannot be damaged if it is the only Mech in your deployment zone.";
        attack = 5;
        defense = 3;
        industryCost = 7;
        losTechCost = 1;
        imageFile = "Gladiator.png";
    }
}
