package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.AC20;

public class Hunchback extends MechUnit {
    public Hunchback() {
        name = "Hunchback";
        subName = "HBK-4G";
        cardText = "AC/20: At the start of your Combat phase, you may reveal the top card of your deck. If it is a Unit or Support card, your opponent must damage a Unit.";
        attack = 1;
        defense = 1;
        industryCost = 4;
        imageFile = "Hunchback2.png";

        addAbility(new AC20(this));
    }
}
