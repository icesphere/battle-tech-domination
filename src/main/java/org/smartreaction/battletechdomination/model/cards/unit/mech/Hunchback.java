package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.AC20;

public class Hunchback extends MechUnit {
    public Hunchback() {
        name = "Hunchback";
        subName = "HBK-4G";
        cardText = "AC/20: You may reveal the top card of your deck at the start of your Combat phase. If it is a... Resource card, your opponent must damage a Mech; Support card, damage this unit; Unit card, no effect.";
        attack = 0;
        defense = 1;
        industryCost = 4;

        addAbility(new AC20(this));
    }
}
