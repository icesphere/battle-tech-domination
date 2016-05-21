package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Afterburner extends UnitAbility implements UnitBuyAbility, UnitChoiceAbility {
    //AFTERBURNER: When you buy this, you may overpay by 2 Industry to immediately place it in your deployment zone.

    public Afterburner(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getIndustry() >= 2) {
            player.makeYesNoUnitAbilityChoice(this, "Do you want to overpay by 2 Industry to immeditely place " + unit.getName() + " in your deployment zone?");
        } else {
            player.cardAcquired(unit);
        }
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addIndustry(-2);
            player.addGameLog(player.getPlayerName() + " chose to use Afterburner ability to overpay by 2 Industry to place " + unit.getName() + " in deployment zone");
            player.deployUnit(unit);
        } else {
            player.cardAcquired(unit);
        }
    }
}
