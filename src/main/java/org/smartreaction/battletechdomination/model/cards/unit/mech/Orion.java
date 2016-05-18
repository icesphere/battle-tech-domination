package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Heroic;
import org.smartreaction.battletechdomination.model.players.Player;

public class Orion extends MechUnit {
    Heroic heroic;

    public Orion() {
        name = "Orion";
        subName = "ON1-K";
        cardText = "HEROIC: If you have fewer Units in your deployment zone than your opponent has in his, +1 Attack and +2 Defense.";
        attack = 2;
        defense = 2;
        industryCost = 6;

        heroic = new Heroic(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        heroic.useAbility(player);
    }
}
