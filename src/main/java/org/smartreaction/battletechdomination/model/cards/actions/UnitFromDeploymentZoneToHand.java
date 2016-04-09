package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class UnitFromDeploymentZoneToHand extends Action {
    public UnitFromDeploymentZoneToHand(String text) {
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getDeploymentZone().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is moving a Unit from their deployment zone to their hand");
            return true;
        }
    }
}
