package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.HighlanderBurial;

public class Highlander extends MechUnit {
    public Highlander() {
        name = "Highlander";
        subName = "HGN-732";
        cardText = "HIGHLANDER BURIAL: You may damage this unit at the start of your Combat phase. If you do, choose a unit costing 5 Industry or less for your opponent to scrap.";
        attack = 2;
        defense = 3;
        industryCost = 7;
        imageFile = "Highlander2.png";

        addAbility(new HighlanderBurial(this));
    }
}
