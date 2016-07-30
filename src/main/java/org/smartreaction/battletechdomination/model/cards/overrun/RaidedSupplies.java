package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsForBenefitActionCard;
import org.smartreaction.battletechdomination.model.cards.actions.DiscardCardsFromHandForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class RaidedSupplies extends OverrunSupport implements DiscardCardsForBenefitActionCard {
    public RaidedSupplies() {
        name = "Raided Supplies";
        cardText = "You may discard 2 cards from your hand.  If you do, scrap this card.";
        overrunAmount = 2;
        imageFile = "RaidedSupplies.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new DiscardCardsFromHandForBenefit(this, 2, "Discard 2 cards from your hand to scrap Raided Supplies"));
    }

    @Override
    public void cardsDiscarded(Player player, List<Card> discardedCards) {
        player.getCardsPlayed().remove(this);
    }

    @Override
    public void onChoseDoNotUse(Player player) {

    }
}
