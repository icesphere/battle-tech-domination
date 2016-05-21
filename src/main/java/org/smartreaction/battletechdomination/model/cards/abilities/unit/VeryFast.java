package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class VeryFast extends UnitAbility implements UnitBuyAbility, UnitChoiceAbility {
    //VERY FAST: When you buy this, you may immediately place it in your deployment zone.

    public VeryFast(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.makeYesNoUnitAbilityChoice(this, "Add " + unit.getName() + " to your deployment zone?");
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog(player.getPlayerName() + " chose to use Very Fast ability to place " + unit.getName() + " in deployment zone");
            player.deployUnit(unit);
        } else {
            player.cardAcquired(unit);
        }
    }
}
