package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Durable;
import org.smartreaction.battletechdomination.model.cards.abilities.Scout;

public class Commando extends MechUnit implements Durable {
    public Commando() {
        name = "Commando";
        subName = "COM-2D";
        cardText = "DURABLE: +1 Card when this unit is damaged. SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.";
        attack = 1;
        defense = 0;
        industryCost = 3;

        addAbility(new Scout(this));
    }
}
