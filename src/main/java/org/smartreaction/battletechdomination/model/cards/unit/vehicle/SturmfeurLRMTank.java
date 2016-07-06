package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.LRMFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Tank;

public class SturmfeurLRMTank extends VehicleUnit {
    public SturmfeurLRMTank() {
        name = "Sturmfeur LRM Tank";
        cardText = "LRM FIRE SUPPORT: Each Mech in your deployment zone gets +1 Attack. TANK: If this unit is damaged, scrap it instead.";
        attack = 1;
        defense = 0;
        industryCost = 2;
        imageFile = "SturmFeur.png";

        addAbility(new LRMFireSupport(this));
        addAbility(new Tank(this));
    }
}
