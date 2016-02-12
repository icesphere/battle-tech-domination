package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.CityFighter;

public class Urbanmech extends MechUnit implements CityFighter {
    public Urbanmech() {
        name = "Urbanmech";
        subName = "UM-R63";
        cardText = "CITY FIGHTER: +1 Attack if your opponent has two or more Infantry units in his deployment zone.";
        attack = 0;
        defense = 1;
        industryCost = 1;
    }
}
