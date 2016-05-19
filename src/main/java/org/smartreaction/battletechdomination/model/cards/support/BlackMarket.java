package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class BlackMarket extends Support {
    public BlackMarket() {
        name = "Black Market";
        cardText = "Scrap a Resource card from your hand. If you do, +3 Industry.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Resource card from your hand to gain +3 Industry."));
    }
}
