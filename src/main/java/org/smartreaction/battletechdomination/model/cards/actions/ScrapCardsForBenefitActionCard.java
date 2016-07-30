package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public interface ScrapCardsForBenefitActionCard {
    void cardsScrapped(Player player, List<Card> scrappedCards);
    boolean isCardApplicable(Card card);
}
