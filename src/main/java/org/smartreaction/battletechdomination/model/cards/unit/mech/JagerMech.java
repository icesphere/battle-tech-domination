package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.SuppressiveFire;

public class JagerMech extends MechUnit {
    public JagerMech() {
        name = "Jager Mech";
        subName = "JM6-A";
        cardText = "SUPPRESSIVE FIRE: +1 Attack if you have more Mechs in your deployment zone than your opponent has in his.";
        attack = 1;
        defense = 2;
        industryCost = 5;
        imageFile = "JagerMech.png";

        addAbility(new SuppressiveFire(this));
    }
}
