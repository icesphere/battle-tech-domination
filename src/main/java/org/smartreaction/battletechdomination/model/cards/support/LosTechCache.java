package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class LosTechCache extends Support {
    public LosTechCache() {
        name = "Los Tech Cache";
        cardText = "+3 Cards. You may ignore the Los Tech cost when buying cards this turn.";
        industryCost = 4;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.drawCards(3);
        player.ignoreLosTechCost();
    }
}
