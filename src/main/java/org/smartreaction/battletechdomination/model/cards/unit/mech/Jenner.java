package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.GuerrillaFighter;

public class Jenner extends MechUnit {
    public Jenner() {
        name = "Jenner";
        subName = "JR7-D";
        cardText = "GUERRILLA FIGHTER: +1 Card when you Overrun your opponent.";
        attack = 1;
        defense = 0;
        industryCost = 2;
        imageFile = "Jenner.png";

        addAbility(new GuerrillaFighter(this));
    }
}
