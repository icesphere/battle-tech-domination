package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.HeavyArmor;

public class Atlas extends MechUnit {
    public Atlas() {
        name = "Atlas";
        subName = "AS7-K";
        cardText = "HEAVY ARMOR: When this unit is damaged, you may shuffle it into your deck.";
        attack = 3;
        defense = 4;
        industryCost = 8;

        addAbility(new HeavyArmor(this));
    }
}
