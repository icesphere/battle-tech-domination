package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportScrapCardsForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScrapCardsFromHandForBenefit extends Action {
    private SupportScrapCardsForBenefit card;
    private int numCardsToScrap;
    private CardType cardType;

    private List<Card> selectedCards = new ArrayList<>();

    public ScrapCardsFromHandForBenefit(SupportScrapCardsForBenefit card, int numCardsToScrap, String text, CardType cardType) {
        this.card = card;
        this.numCardsToScrap = numCardsToScrap;
        this.text = text;
        this.cardType = cardType;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND) && !selectedCards.contains(card) && (cardType == null || cardType == card.getCardType());
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            List<Card> cardsAvailableToScrap;
            if (cardType != null) {
                cardsAvailableToScrap = player.getHand().stream().filter(c -> c.getCardType() == cardType).collect(Collectors.toList());
            } else {
                cardsAvailableToScrap = new ArrayList<>(player.getHand());
            }
            if (numCardsToScrap > cardsAvailableToScrap.size()) {
                cardsAvailableToScrap.stream().forEach(player::scrapCardFromHand);
                card.cardsScrapped(player, cardsAvailableToScrap);
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        selectedCards.stream().forEach(player::scrapCardFromHand);
        card.cardsScrapped(player, selectedCards);
    }

    public int getNumCardsToScrap() {
        return numCardsToScrap;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    @Override
    public boolean showActionDialog() {
        return selectedCards.size() == 0;
    }
}
