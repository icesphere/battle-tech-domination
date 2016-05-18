package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Overheat;
import org.smartreaction.battletechdomination.model.players.Player;

public class Awesome extends MechUnit {
    Overheat overheat;

    public Awesome() {
        name = "Awesome";
        subName = "AWS-9M";
        cardText = "OVERHEAT: You may damage this unit at the start of your combat phase. If you do, +4 Attack.";
        attack = 1;
        defense = 3;
        industryCost = 6;

        overheat = new Overheat(this);
    }

    @Override
    public void useUnitAbility(Player player) {
        overheat.useAbility(player);
    }
}
