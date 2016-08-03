package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class MerchantCaste extends Support {
    public MerchantCaste() {
        name = "Merchant Caste";
        cardText = "Gain a card in the Supply without paying its cost.";
        industryCost = 6;
        losTechCost = 1;
        imageFile = "MerchantCaste.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.acquireFreeCardInSupply();
    }
}
