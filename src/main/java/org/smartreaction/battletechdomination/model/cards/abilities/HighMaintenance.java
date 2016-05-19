package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class HighMaintenance extends Ability implements UnitDeployedAbility {
    //HIGH MAINTENANCE: Discard a card when you deploy this unit.

    public HighMaintenance(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " must discard a card due to the High Maintenance ability on " + unit.getName());
        player.discardCardFromHand();
    }
}
