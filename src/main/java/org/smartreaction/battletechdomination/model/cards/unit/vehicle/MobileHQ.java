package org.smartreaction.battletechdomination.model.cards.unit.vehicle;

import org.smartreaction.battletechdomination.model.cards.VehicleUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.TacticalCommand;

public class MobileHQ extends VehicleUnit implements TacticalCommand {
    public MobileHQ() {
        name = "MobileHQ";
        cardText = "TACTICAL COMMAND: Draw 2 extra cards during your Clean-Up phase, then discard down to 5 cards in your hand.  If this unit is damaged or scrapped, your opponent immediately draws 2 cards.";
        attack = 0;
        defense = 0;
        industryCost = 6;
    }
}
