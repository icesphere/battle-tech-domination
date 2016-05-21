package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SuppressiveFire;

public class BlackJack extends MechUnit {
    public BlackJack() {
        name = "Black Jack";
        subName = "BJ-1";
        cardText = "SUPPRESSIVE FIRE: +1 Attack if you have more Mechs in your deployment zone than your opponent has in his.";
        attack = 0;
        defense = 2;
        industryCost = 3;

        addAbility(new SuppressiveFire(this));
    }
}
