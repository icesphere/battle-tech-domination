package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class Refinery extends Support {
    public Refinery() {
        name = "Refinery";
        cardText = "Scrap a Resource card from your hand. Gain a Resource card costing up to 3 Industry more than the card you scrapped. Put the gained card into your hand.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Resource card from your hand. Gain a Resource card costing up to 3 Industry more than the card you scrapped. The gained card will be put into your hand."));
    }
}
