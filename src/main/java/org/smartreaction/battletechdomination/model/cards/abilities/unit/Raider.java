package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class Raider extends UnitAbility implements UnitDeployedAbility {
    //RAIDER: When you deploy this unit, your opponent must discard a card from his hand.

    public Raider(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getOpponent().getPlayerName() + " must discard a card due to Raider ability on " + unit.getName());
        player.addOpponentAction(new DiscardCardsFromHand(1, "Discard a card"));
    }
}
