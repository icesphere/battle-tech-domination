package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.HeavyFireSupport;

public class Catapult extends MechUnit {
    public Catapult() {
        name = "Catapult";
        subName = "CPLT-C1";
        cardText = "HEAVY FIRE SUPPORT: You may discard a Unit card from your hand at the start of your Combat phase. If you do, your opponent must damage a unit.";
        attack = 0;
        defense = 2;
        industryCost = 5;
        imageFile = "Catapult.png";

        addAbility(new HeavyFireSupport(this));
    }
}
