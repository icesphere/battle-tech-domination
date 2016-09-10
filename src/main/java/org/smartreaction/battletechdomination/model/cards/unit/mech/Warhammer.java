package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Intimidating;

public class Warhammer extends MechUnit {
    public Warhammer() {
        name = "Warhammer";
        subName = "WHM-7M";
        cardText = "Intimidating: When you deploy this unit, your opponent must return a unit from their deployment zone to their hand (your choice).";
        attack = 2;
        defense = 2;
        industryCost = 6;
        imageFile = "Warhammer.png";

        addAbility(new Intimidating(this));
    }
}
