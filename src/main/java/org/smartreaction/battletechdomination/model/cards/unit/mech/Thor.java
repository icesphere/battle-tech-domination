package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.DeathFromAbove;

public class Thor extends MechUnit {
    public Thor() {
        name = "Thor";
        subName = "SUMMONER";
        cardText = "DEATH FROM ABOVE: You may damage this unit at the start of your Combat phase. If you do, choose an opposing unit costing 5 Industry or less for your opponent to scrap.";
        attack = 3;
        defense = 3;
        industryCost = 6;
        losTechCost = 1;

        addAbility(new DeathFromAbove(this));
    }
}
