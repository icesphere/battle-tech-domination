package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Durable;
import org.smartreaction.battletechdomination.model.cards.abilities.Scout;
import org.smartreaction.battletechdomination.model.players.Player;

public class Commando extends MechUnit implements Durable {
    Scout scout;

    public Commando() {
        name = "Commando";
        subName = "COM-2D";
        cardText = "DURABLE: +1 Card when this unit is damaged. SCOUT: At the start of your Combat phase, your opponent reveals the top card of his deck and either puts it back or discards it, your choice.";
        attack = 1;
        defense = 0;
        industryCost = 3;

        scout = new Scout(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return scout.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        scout.useAbility(player);
    }
}
