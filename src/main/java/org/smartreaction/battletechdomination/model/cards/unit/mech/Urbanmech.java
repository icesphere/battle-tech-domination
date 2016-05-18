package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.CityFighter;
import org.smartreaction.battletechdomination.model.players.Player;

public class Urbanmech extends MechUnit {
    CityFighter cityFighter;

    public Urbanmech() {
        name = "Urbanmech";
        subName = "UM-R63";
        cardText = "CITY FIGHTER: +3 Attack if your opponent has two or more Infantry units in his deployment zone.";
        attack = 0;
        defense = 1;
        industryCost = 1;

        cityFighter = new CityFighter(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        cityFighter.useAbility(player);
    }
}
