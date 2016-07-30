package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class ScrapCardsFromHandOrDiscardPileForBenefit extends ScrapCardsFromHandOrDiscardPile {
    private ScrapCardsForBenefitActionCard scrapCardsForBenefitActionCard;

    public ScrapCardsFromHandOrDiscardPileForBenefit(ScrapCardsForBenefitActionCard card, int numCardsToScrap, String text) {
        super(numCardsToScrap, text);
        this.scrapCardsForBenefitActionCard = card;
    }

    public ScrapCardsFromHandOrDiscardPileForBenefit(ScrapCardsForBenefitActionCard card, int numCardsToScrap, String text, boolean optional) {
        super(numCardsToScrap, text);
        this.scrapCardsForBenefitActionCard = card;
        this.optional = optional;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return super.isCardActionable(card, cardLocation, player) && scrapCardsForBenefitActionCard.isCardApplicable(card);
    }

    @Override
    public boolean processAction(Player player) {
        boolean hasApplicableCardInHand = player.getHand().stream().anyMatch(c -> scrapCardsForBenefitActionCard.isCardApplicable(c));
        boolean hasApplicableCardInDiscard = player.getDiscard().stream().anyMatch(c -> scrapCardsForBenefitActionCard.isCardApplicable(c));
        return hasApplicableCardInHand || hasApplicableCardInDiscard;
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        boolean doneWithAction = super.processActionResult(player, result);

        if (doneWithAction) {
            scrapCardsForBenefitActionCard.cardsScrapped(player, getAllSelectedCards());
        }

        return doneWithAction;
    }
}
