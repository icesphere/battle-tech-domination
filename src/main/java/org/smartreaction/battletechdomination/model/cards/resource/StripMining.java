package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapCardFromHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class StripMining extends Resource {
    public StripMining() {
        name = "Strip Mining";
        cardText = "Scrap a Resource card from your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new ScrapCardFromHand(CardType.RESOURCE, "Scrap a Resource card from your hand"));
    }
}
