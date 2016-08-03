package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapCardsForBenefitActionCard;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapCardsFromHandOrDiscardPileForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class WarriorCaste extends Support implements ScrapCardsForBenefitActionCard {
    public WarriorCaste() {
        name = "Warrior Caste";
        cardText = "+2 Actions. Scrap up to 2 Resource cards from your hand and/or discard pile. Draw a card for each card scrapped in this way.";
        industryCost = 7;
        losTechCost = 1;
        imageFile = "WarriorCaste.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(2);
        player.addAction(new ScrapCardsFromHandOrDiscardPileForBenefit(this, 2, "Scrap up to 2 Resource cards from your hand and/or discard pile to draw a card for each card scrapped."));
    }

    @Override
    public void cardsScrapped(Player player, List<Card> scrappedCards) {
        if (!scrappedCards.isEmpty()) {
            player.drawCards(scrappedCards.size());
        }
    }

    @Override
    public boolean isCardApplicable(Card card) {
        return card.isResource();
    }
}
