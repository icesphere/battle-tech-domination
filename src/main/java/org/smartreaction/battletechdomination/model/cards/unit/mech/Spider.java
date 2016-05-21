package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Raider;

public class Spider extends MechUnit {
    public Spider() {
        name = "Spider";
        subName = "SDR-5V";
        cardText = "RAIDER: When you deploy this unit, your opponent must discard a card from his hand.";
        attack = 1;
        defense = 0;
        industryCost = 1;

        addAbility(new Raider(this));
    }
}
