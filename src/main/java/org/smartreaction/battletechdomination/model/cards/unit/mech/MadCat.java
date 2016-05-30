package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.OverwhelmingAttack;

public class MadCat extends MechUnit {
    public MadCat() {
        name = "Mad Cat";
        subName = "TIMBERWOLF";
        cardText = "OVERWHELMING ATTACK: When you deploy this unit, your opponent damages a unit costing 5 Industry or less of your choice.";
        attack = 4;
        defense = 3;
        industryCost = 7;
        losTechCost = 1;
        imageFile = "madcat_v2.jpeg";

        addAbility(new OverwhelmingAttack(this));
    }
}
