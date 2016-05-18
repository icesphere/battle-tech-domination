package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Inspiring;
import org.smartreaction.battletechdomination.model.players.Player;

public class VeteranInfantry extends InfantryUnit {
    Inspiring inspiring;

    public VeteranInfantry() {
        name = "Veteran Infantry";
        cardText = "INSPIRING: +1 Attack per Infantry Platoon in your deployment zone.";
        attack = 1;
        defense = 1;
        industryCost = 2;

        inspiring = new Inspiring(this);
    }

    @Override
    public void applyCombatPhaseBonuses(Player player) {
        inspiring.useAbility(player);
    }
}
