package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;

import java.util.List;

public class BattlefieldSalvage extends Support {
    public BattlefieldSalvage() {
        name = "Battlefield Salvage";
        cardText = "+2 Industry. You may discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        List<Card> cards = player.discardCardsFromHandOrDeploymentZone(1, true);
        if (!cards.isEmpty()) {
            Card card = cards.get(0);
            player.addIndustry(card.getIndustryCost());
        }
    }
}
