package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class SocialGenerals extends Support {
    public SocialGenerals() {
        name = "Social Generals";
        cardText = "+2 Industry. Until the end of your turn, when you buy or gain a card, you may put it on top of your deck.";
        industryCost = 4;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.mayPutBoughtOrGainedCardsOnTopOfDeck();
    }
}
