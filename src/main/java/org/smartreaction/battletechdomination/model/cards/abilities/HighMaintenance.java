package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class HighMaintenance extends Ability implements UnitDeployedAbility {
    //HIGH MAINTENANCE: Discard a card when you deploy this unit.

    public HighMaintenance(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " must discard a card due to the High Maintenance ability on " + card.getName());
        player.discardCardFromHand();
    }
}
