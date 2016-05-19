package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Versatile extends UnitAbility implements UnitDeployedAbility, UnitChoiceAbility {
    //VERSATILE: When you deploy this unit, choose one: +1 Card; +1 Action; +1 Industry

    public Versatile(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Choice choice1 = new Choice(1, "+1 Card");
        Choice choice2 = new Choice(2, "+1 Action");
        Choice choice3 = new Choice(3, "+1 Industry");

        player.makeUnitAbilityChoice(this, "Choose one", choice1, choice2, choice3);
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog("Chose +1 Card");
            player.drawCards(1);
        } else if (choice == 2) {
            player.addGameLog("Chose +1 Action");
            player.addActions(1);
        } else if (choice == 3) {
            player.addGameLog("Chose +1 Industry");
            player.addIndustry(1);
        }
    }
}
