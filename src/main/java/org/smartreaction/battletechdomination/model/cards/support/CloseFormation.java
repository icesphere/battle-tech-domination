package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsForBenefitActionCard;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHandForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class CloseFormation extends Support implements DiscardCardsForBenefitActionCard {
    public CloseFormation() {
        name = "Close Formation";
        cardText = "+1 Action. Discard any number of cards from your hand. +1 Card per card discarded in this way.";
        industryCost = 3;
        imageFile = "CloseFormation.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        player.addAction(new DiscardCardsFromHandForBenefit(this, 1000, "Discard any number of cards from your hand for +1 Card per card discarded.", true));
    }

    @Override
    public void cardsDiscarded(Player player, List<Card> discardedCards) {
        player.drawCards(discardedCards.size());
    }

    @Override
    public void onChoseDoNotUse(Player player) {
        //do nothing
    }
}
