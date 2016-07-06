package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.LaserBarrage;

public class BlackHawk extends MechUnit {
    public BlackHawk() {
        name = "Black Hawk";
        subName = "NOVA";
        cardText = "LASER BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a Unit card, +2 Attack.";
        attack = 3;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;
        imageFile = "BlackHawk.png";

        addAbility(new LaserBarrage(this));
    }
}
