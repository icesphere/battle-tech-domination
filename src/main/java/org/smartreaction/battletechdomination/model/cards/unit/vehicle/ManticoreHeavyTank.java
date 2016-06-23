package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Tank;

public class ManticoreHeavyTank extends VehicleUnit {
    public ManticoreHeavyTank() {
        name = "Manticore Heavy Tank";
        cardText = "TANK: If this unit is damaged, scrap it instead.";
        attack = 1;
        defense = 1;
        industryCost = 1;
        imageFile = "Manticore.png";

        addAbility(new Tank(this));
    }
}
