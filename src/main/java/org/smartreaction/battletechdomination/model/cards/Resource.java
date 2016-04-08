package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Resource extends Card {
    protected Resource() {
        cardType = CardType.RESOURCE;
    }

    @Override
    public boolean isActionable(Player player, String cardLocation) {
        return player.isYourTurn() && cardLocation.equals(Card.CARD_LOCATION_HAND) && player.isBuyPhase();
    }
}
