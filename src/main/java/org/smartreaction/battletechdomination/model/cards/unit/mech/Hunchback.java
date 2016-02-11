package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Hunchback extends MechUnit {
    public Hunchback() {
        name = "Hunchback";
        subName = "HBK-4G";
        cardText = "AC/20: Once per turn during your action phase, you may reveal the top card of your deck. If it is a... Resource card, your opponent must damage a Mech; Support card, damage this unit; Unit card, no effect.";
        attack = 0;
        defense = 1;
        industryCost = 4;
    }

    //todo
}
