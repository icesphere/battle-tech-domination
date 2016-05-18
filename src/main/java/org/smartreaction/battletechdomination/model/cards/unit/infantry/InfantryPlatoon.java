package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Expendable;
import org.smartreaction.battletechdomination.model.players.Player;

public class InfantryPlatoon extends InfantryUnit implements BaseSupply {
    Expendable expendable;

    public InfantryPlatoon() {
        name = "Infantry Platoon";
        cardText = "EXPENDABLE: You may damage this unit at the start of your Combat phase. If you do, +1 Attack.";
        attack = 0;
        defense = 1;
        industryCost = 0;

        expendable = new Expendable(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return expendable.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        expendable.useAbility(player);
    }
}
