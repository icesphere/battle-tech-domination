package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Trebuchet extends MechUnit {
    public Trebuchet() {
        name = "Trebuchet";
        subName = "TBT-5N";
        cardText = "MOBILE FIRE SUPPORT: At the start of your Combat phase (or your opponent's), you may discard a card from your hand. If you do, +1 Attack (or +1 Defense).";
        attack = 1;
        defense = 1;
        industryCost = 3;
    }

    //todo
}
