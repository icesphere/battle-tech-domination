package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.ChampionMech;

public class Kodiak extends MechUnit {
    public Kodiak() {
        name = "Kodiak";
        cardText = "CHAMPION MECH: When you deploy this unit, +2 cards if your opponent has more units in his deployment zone than you have in yours.";
        attack = 6;
        defense = 4;
        industryCost = 8;
        losTechCost = 1;
        imageFile = "Kodiak.png";

        addAbility(new ChampionMech(this));
    }
}
