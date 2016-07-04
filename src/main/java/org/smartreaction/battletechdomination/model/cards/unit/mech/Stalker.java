package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.PoorHeatManagement;

public class Stalker extends MechUnit {
    public Stalker() {
        name = "Stalker";
        subName = "STK-5M";
        cardText = "POOR HEAT MANAGEMENT: When you Overrun your opponent, discard a card and shuffle this unit into your deck.";
        attack = 4;
        defense = 2;
        industryCost = 6;
        imageFile = "Stalker.png";

        addAbility(new PoorHeatManagement(this));
    }
}
