package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.CounterAttack;

public class Ryoken extends MechUnit {
    public Ryoken() {
        name = "Ryoken";
        subName = "STORMCROW";
        cardText = "COUNTER ATTACK: When this unit is damaged, your opponent must damage a Mech.";
        attack = 3;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;
        imageFile = "Ryoken.png";

        addAbility(new CounterAttack(this));
    }
}
