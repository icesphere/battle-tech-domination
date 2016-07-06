package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Striker;

public class Koshi extends MechUnit {
    public Koshi() {
        name = "Koshi";
        subName = "MIST LYNX";
        cardText = "STRIKER: At the start of your Combat phase, your opponent reveals and discards the top card of his deck. If it is a Resource card, +1 Card.";
        attack = 1;
        defense = 0;
        industryCost = 2;
        losTechCost = 1;
        imageFile = "Koshi.png";

        addAbility(new Striker(this));
    }
}
