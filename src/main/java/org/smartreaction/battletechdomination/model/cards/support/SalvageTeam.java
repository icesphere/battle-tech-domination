package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.players.Player;

public class SalvageTeam extends Support {
    public SalvageTeam() {
        name = "Salvage Team";
        cardText = "+2 Industry. Until the end of your turn, when you buy or gain a card, put it on top of your deck.";
        industryCost = 5;
        imageFile = "SalvageTeam.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.mayPutBoughtOrGainedCardsOnTopOfDeck();
    }
}
