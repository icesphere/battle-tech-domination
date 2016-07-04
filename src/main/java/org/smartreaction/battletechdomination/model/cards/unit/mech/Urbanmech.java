package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.CityFighter;

public class Urbanmech extends MechUnit {
    public Urbanmech() {
        name = "Urbanmech";
        subName = "UM-R63";
        cardText = "CITY FIGHTER: +3 Attack if your opponent has two or more Infantry units in his deployment zone.";
        attack = 0;
        defense = 1;
        industryCost = 1;
        imageFile = "UrbanMech.png";

        addAbility(new CityFighter(this));
    }
}
