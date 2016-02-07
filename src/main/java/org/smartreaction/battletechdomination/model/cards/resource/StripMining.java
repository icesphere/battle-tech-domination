package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class StripMining extends Resource {
    public StripMining() {
        name = "Strip Mining";
        cardText = "+1 Industry. You may scrap a Resource card from your hand or discard pile.";
        industryCost = 2;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);
        player.scrapCardFromHandOrDiscard(CardType.RESOURCE);
    }
}
