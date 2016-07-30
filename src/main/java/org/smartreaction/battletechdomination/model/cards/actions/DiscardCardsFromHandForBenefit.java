package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.players.Player;

public class DiscardCardsFromHandForBenefit extends DiscardCardsFromHand {
    private DiscardCardsForBenefitActionCard discardCardsForBenefitActionCard;

    public DiscardCardsFromHandForBenefit(DiscardCardsForBenefitActionCard card, int numCardsToDiscard, String text) {
        super(numCardsToDiscard, text);
        this.discardCardsForBenefitActionCard = card;
    }

    public DiscardCardsFromHandForBenefit(DiscardCardsForBenefitActionCard card, int numCardsToDiscard, String text, boolean optional) {
        super(numCardsToDiscard, text, optional);
        this.discardCardsForBenefitActionCard = card;
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        boolean doneWithAction = super.processActionResult(player, result);

        if (doneWithAction) {
            discardCardsForBenefitActionCard.cardsDiscarded(player, selectedCards);
        }

        return doneWithAction;
    }

    @Override
    public void onNotUsed(Player player) {
        discardCardsForBenefitActionCard.onChoseDoNotUse(player);
    }
}
