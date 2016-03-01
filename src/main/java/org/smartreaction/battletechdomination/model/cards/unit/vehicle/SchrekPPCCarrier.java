package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Tank;

public class SchrekPPCCarrier extends VehicleUnit implements Tank {
    public SchrekPPCCarrier() {
        name = "Schrek PPC Carrier";
        cardText = "TANK: If this unit is damaged, scrap it instead.";
        attack = 1;
        defense = 2;
        industryCost = 2;
    }
}
