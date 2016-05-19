package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.CounterAttack;

public class Stormcrow extends MechUnit {
    public Stormcrow() {
        name = "Stormcrow";
        subName = "RYOKEN";
        cardText = "COUNTER ATTACK: When this unit is damaged, your opponent must damage a Mech.";
        attack = 3;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;

        addAbility(new CounterAttack(this));
    }
}
