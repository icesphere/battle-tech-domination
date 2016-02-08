package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Refinery extends Support {
    public Refinery() {
        name = "Refinery";
        cardText = "Scrap a Resource card from your hand. Gain a Resource card costing up to 3 Industry more than the card you scrapped. Put the gained card into your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.scrapCardFromHand(CardType.RESOURCE, false);
        //todo
    }
}
