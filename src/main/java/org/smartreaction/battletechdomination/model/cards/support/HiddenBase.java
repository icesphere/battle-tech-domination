package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class HiddenBase extends Support {
    public HiddenBase() {
        name = "Hidden Base";
        cardText = "+1 Card. +1 Action. Set aside a card from your hand face down. At the start of your next turn, put the card into your hand.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(1);
        player.addActions(1);
        player.setAsideCardFromHand();
    }
}
