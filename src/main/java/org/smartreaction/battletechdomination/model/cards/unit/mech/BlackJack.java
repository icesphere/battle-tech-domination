package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SuppressiveFire;

public class BlackJack extends MechUnit {
    public BlackJack() {
        name = "Black Jack";
        subName = "BJ-1";
        cardText = "SUPPRESSIVE FIRE: +2 Attack if you have more Mechs in your deployment zone than your opponent has in his.";
        attack = 1;
        defense = 1;
        industryCost = 3;
        imageFile = "BlackJack.png";

        addAbility(new SuppressiveFire(this));
    }
}
