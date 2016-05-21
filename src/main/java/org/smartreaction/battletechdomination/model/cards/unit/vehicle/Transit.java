package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.Aerospace;

public class Transit extends VehicleUnit {
    public Transit() {
        name = "Transit";
        subName = "TR-10";
        cardText = "AEROSPACE: Damage this unit if you are Overrun or if you Overrun your opponent.";
        attack = 2;
        defense = 1;
        industryCost = 3;

        addAbility(new Aerospace(this));
    }
}
