package org.smartreaction.battletechdomination.model.cards.abilities.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public interface SupportScrapCardsForBenefit {
    void cardsScrapped(Player player, List<Card> scrappedCards);
}
