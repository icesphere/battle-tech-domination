package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class InvasionFleet extends Support {
    public InvasionFleet() {
        name = "Invasion Fleet";
        cardText = "+2 Actions. Reveal your hand. If you do not have any Unit or Support cards in your hand, +2 Cards.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(2);
        player.revealHand();
        if (!player.getHand().stream().anyMatch(c -> c.isUnit() || c.isSupport())) {
            player.drawCards(2);
        }
    }
}
