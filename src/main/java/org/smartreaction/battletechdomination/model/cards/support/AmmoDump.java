package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class AmmoDump extends Support {
    public AmmoDump() {
        name = "Ammo Dump";
        cardText = "+3 Cards";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
    }
}
