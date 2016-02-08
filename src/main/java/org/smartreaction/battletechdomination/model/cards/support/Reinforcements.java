package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Reinforcements extends Support {
    public Reinforcements() {
        name = "Reinforcements";
        cardText = "+1 Action. Reveal the top 4 cards of your deck. Put the revealed Unit cards into your hand. Put the other cards on top of your deck in any order.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        //todo
    }
}
