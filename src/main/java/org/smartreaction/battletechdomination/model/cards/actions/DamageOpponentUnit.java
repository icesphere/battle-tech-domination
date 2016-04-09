package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class DamageOpponentUnit extends Action {
    public DamageOpponentUnit(String text) {
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_OPPONENT_UNITS);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getOpponent().getDeploymentZone().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is choosing an opponent's unit to damage");
            return true;
        }
    }
}
