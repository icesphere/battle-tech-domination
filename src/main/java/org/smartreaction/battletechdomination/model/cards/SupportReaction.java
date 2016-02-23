package org.smartreaction.battletechdomination.model.cards;

import org.smartreaction.battletechdomination.model.players.Player;

public abstract class SupportReaction extends Card {
    protected SupportReaction() {
        cardType = CardType.SUPPORT_REACTION;
    }

    @Override
    public void cardPlayed(Player player) {

    }
}
