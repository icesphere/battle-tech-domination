package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.abilities.HighMaintenance;
import org.smartreaction.battletechdomination.model.cards.MechUnit;

public class Centurion extends MechUnit implements HighMaintenance {
    public Centurion() {
        name = "Centurion";
        subName = "CN9-D";
        cardText = "HIGH MAINTENANCE: Discard a card when you deploy this unit.";
        attack = 1;
        defense = 2;
        industryCost = 3;
    }
}
