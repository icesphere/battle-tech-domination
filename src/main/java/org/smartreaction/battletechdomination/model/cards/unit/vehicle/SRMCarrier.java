package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Tank;

public class SRMCarrier extends VehicleUnit {
    public SRMCarrier() {
        name = "SRM Carrier";
        cardText = "TANK: If this unit is damaged, scrap it instead.";
        attack = 2;
        defense = 0;
        industryCost = 2;

        addAbility(new Tank(this));
    }
}
