package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public interface DiscardCardsForBenefitActionCard {
    void cardsDiscarded(Player player, List<Card> discardedCards);
    void onChoseDoNotUse(Player player);
}
