package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class QuickToAction extends UnitAbility implements UnitBuyAbility, UnitChoiceAbility {
    //QUICK TO ACTION: When you buy this unit, you may place it on top of your deck instead of your discard pile.

    public QuickToAction(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.makeYesNoUnitAbilityChoice(this, "Add " + unit.getName() + " to top of deck?");
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog(player.getPlayerName() + " chose to use Quick To Action ability to put " + unit.getName() + " on top of deck");
            player.addCardToTopOfDeck(unit);
        } else {
            player.cardAcquired(unit);
        }
    }
}
