package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.AC10;

public class Centurion extends MechUnit {
    public Centurion() {
        name = "Centurion";
        subName = "CN9-D";
        cardText = "AC/10: +1 Attack if your opponent has a Mech or Vehicle in his deployment zone.";
        attack = 1;
        defense = 1;
        industryCost = 3;

        addAbility(new AC10(this));
    }
}
