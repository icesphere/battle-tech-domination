package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Support extends Card {
    protected Support() {
        cardType = CardType.SUPPORT;
    }

    @Override
    public boolean isActionable(Player player, String cardLocation) {
        return player.isYourTurn() && cardLocation.equals(Card.CARD_LOCATION_HAND) && player.isActionPhase() && player.getActions() > 0;
    }
}
