package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyArmor extends UnitAbility implements UnitDamagedAbility, UnitChoiceAbility {
    //HEAVY ARMOR: When this unit is damaged, you may shuffle it into your deck.

    public HeavyArmor(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.makeYesNoUnitAbilityChoice(this, "Shuffle " + unit.getName() + " into deck?");
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog(player.getPlayerName() + " chose to use Heavy Armor ability to shuffle " + unit.getName() + " into deck");
            player.shuffleCardIntoDeck(unit);
        } else {
            player.addCardToDiscard(unit);
            player.cardRemovedFromPlay(unit);
        }
    }
}
