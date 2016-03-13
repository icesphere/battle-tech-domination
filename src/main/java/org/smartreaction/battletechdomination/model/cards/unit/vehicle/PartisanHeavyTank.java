package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Tank;

public class PartisanHeavyTank extends VehicleUnit implements Tank {
    public PartisanHeavyTank() {
        name = "Partisan Heavy Tank";
        cardText = "TANK: If this unit is damaged, scrap it instead.";
        attack = 0;
        defense = 2;
        industryCost = 0;
    }
}
