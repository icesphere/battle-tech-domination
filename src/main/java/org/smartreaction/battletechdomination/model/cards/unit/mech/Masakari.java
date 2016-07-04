package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.QuadERPPCs;

public class Masakari extends MechUnit {
    public Masakari() {
        name = "Masakari";
        subName = "WARHAWK";
        cardText = "QUAD ER PPCs: You may discard two cards from your hand at the start of your Combat phase. If you do, your opponent gains a Heavy Casualties card.";
        attack = 4;
        defense = 4;
        industryCost = 8;
        losTechCost = 1;
        imageFile = "Masakari.png";

        addAbility(new QuadERPPCs(this));
    }
}
