package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Overheat;

public class Awesome extends MechUnit {
    public Awesome() {
        name = "Awesome";
        subName = "AWS-9M";
        cardText = "OVERHEAT: You may damage this unit at the start of your combat phase. If you do, +4 Attack.";
        attack = 1;
        defense = 3;
        industryCost = 6;
        imageFile = "Awesome.png";

        addAbility(new Overheat(this));
    }
}
