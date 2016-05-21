package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Brawler;

public class Wolverine extends MechUnit {
    public Wolverine() {
        name = "Wolverine";
        subName = "WVR-7D";
        cardText = "BRAWLER: When this unit is damaged, your opponent must damage a unit.";
        attack = 1;
        defense = 2;
        industryCost = 4;

        addAbility(new Brawler(this));
    }
}
