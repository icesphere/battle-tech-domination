package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.MobileFireSupport;

public class Trebuchet extends MechUnit {
    public Trebuchet() {
        name = "Trebuchet";
        subName = "TBT-5N";
        cardText = "MOBILE FIRE SUPPORT: At the start of your Combat phase, you may discard a card from your hand. If you do, +1 Attack.";
        attack = 1;
        defense = 1;
        industryCost = 4;
        imageFile = "Trebuchet.png";

        addAbility(new MobileFireSupport(this));
    }
}
