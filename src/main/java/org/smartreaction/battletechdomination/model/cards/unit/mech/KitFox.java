package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class KitFox extends MechUnit {
    public KitFox() {
        name = "Kit Fox";
        subName = "ULLER";
        cardText = "VERSATILE: When you deploy this unit, choose one: +1 Card; +1 Action; +1 Industry. QUICK TO ACTION: When you buy this unit, you may place it on top of your deck instead of your discard pile.";
        attack = 2;
        defense = 1;
        industryCost = 3;
        losTechCost = 1;
    }

    //todo
}
