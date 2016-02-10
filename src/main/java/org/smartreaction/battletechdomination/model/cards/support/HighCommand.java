package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class HighCommand extends Support {
    public HighCommand() {
        name = "High Command";
        cardText = "Reveal the top 5 cards of your deck. Your opponent chooses one of those cards to discard. Place the other cards into your hand.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        //todo
    }
}
