package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.LaserBarrage;

public class BlackHawk extends MechUnit {
    public BlackHawk() {
        name = "Black Hawk";
        subName = "NOVA";
        cardText = "LASER BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a... Resource card, X = 3; Unit card, X = 5; Otherwise, shuffle this unit into your deck.";
        attack = 0;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;

        addAbility(new LaserBarrage(this));
    }
}
