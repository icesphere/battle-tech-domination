package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.LRMFireSupport;

public class Vulture extends MechUnit {
    public Vulture() {
        name = "Vulture";
        subName = "MAD DOG";
        cardText = "LRM FIRE SUPPORT: Each other Mech in your deployment zone gets +1 Attack.";
        attack = 2;
        defense = 2;
        industryCost = 5;
        losTechCost = 1;
        imageFile = "Vulture.png";

        addAbility(new LRMFireSupport(this));
    }
}
