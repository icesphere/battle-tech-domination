package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;

public class Cheetah extends VehicleUnit {
    public Cheetah() {
        name = "Cheetah";
        subName = "F-10";
        cardText = "AFTERBURNER: When you buy this, you may overpay by 2 Industry to immediately place it in your deployment zone. AEROSPACE: Damage this unit if you are Overrun or if you Overrun your opponent.";
        attack = 1;
        defense = 1;
        industryCost = 2;
    }

    //todo
}
