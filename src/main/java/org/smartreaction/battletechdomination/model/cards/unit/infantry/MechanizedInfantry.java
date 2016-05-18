package org.smartreaction.battletechdomination.model.cards.unit.infantry;

import org.smartreaction.battletechdomination.model.cards.InfantryUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.Expendable;
import org.smartreaction.battletechdomination.model.cards.abilities.Mobile;
import org.smartreaction.battletechdomination.model.players.Player;

public class MechanizedInfantry extends InfantryUnit {
    Expendable expendable;
    Mobile mobile;

    public MechanizedInfantry() {
        name = "Mechanized Infantry";
        cardText = "MOBILE: +1 Action when you deploy this unit. EXPENDABLE: You may damage this unit at the start of your Combat phase.  If you do, +1 Attack.";
        attack = 0;
        defense = 1;
        industryCost = 2;

        expendable = new Expendable(this);
        mobile = new Mobile(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return expendable.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        expendable.useAbility(player);
    }

    @Override
    public void unitDeployed(Player player) {
        mobile.useAbility(player);
    }
}
