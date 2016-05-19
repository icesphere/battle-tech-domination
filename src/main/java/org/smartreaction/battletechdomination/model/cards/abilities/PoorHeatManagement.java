package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class PoorHeatManagement extends Ability implements OverrunOpponentAbility {
    //POOR HEAT MANAGEMENT: When you Overrun your opponent, discard a card and shuffle this unit into your deck.

    public PoorHeatManagement(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getPlayerName() + " has to discard a card due to Poor Heat Management ability on " + unit.getName());
        player.discardCardsFromHand(1);
        player.getDeploymentZone().remove(unit);
        player.shuffleCardIntoDeck(unit);
    }
}
