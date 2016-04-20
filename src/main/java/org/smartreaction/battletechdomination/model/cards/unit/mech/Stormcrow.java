package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.CounterAttack;

public class Stormcrow extends MechUnit implements CounterAttack {
    public Stormcrow() {
        name = "Stormcrow";
        subName = "RYOKEN";
        cardText = "COUNTER ATTACK: When this unit is damaged, your opponent must damage a Mech.";
        attack = 3;
        defense = 1;
        industryCost = 4;
        losTechCost = 1;
    }
}
