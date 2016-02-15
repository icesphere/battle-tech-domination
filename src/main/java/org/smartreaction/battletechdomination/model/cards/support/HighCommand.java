package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class HighCommand extends Support {
    public HighCommand() {
        name = "High Command";
        cardText = "Reveal the top 5 cards of your deck. Discard highest cost card (if multiple tied for highest cost discard one at random). Place the other cards into your hand.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        //todo - need to figure out how Los Tech factors into the highest cost card
    }
}
