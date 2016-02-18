package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.TargetingComputer;

public class Adder extends MechUnit implements TargetingComputer {
    public Adder() {
        name = "Adder";
        subName = "PUMA";
        cardText = "TARGETING COMPUTER: +1 Card when you damage an OPPOSING Mech.";
        attack = 3;
        defense = 1;
        industryCost = 3;
        losTechCost = 1;
    }
}
