package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class NovaCat extends MechUnit {
    public NovaCat() {
        name = "Nova Cat";
        cardText = "LONG RANGE BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a... Resource card, your opponent must discard a card; Unit card, your opponent must damage a unit; Otherwise, no effect.";
        attack = 4;
        defense = 2;
        industryCost = 7;
        losTechCost = 1;
    }

    //todo
}
