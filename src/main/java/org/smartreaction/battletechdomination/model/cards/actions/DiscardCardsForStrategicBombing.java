package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class DiscardCardsForStrategicBombing extends Action {
    private List<Card> revealedCards;

    public DiscardCardsForStrategicBombing(List<Card> revealedCards, String text) {
        this.revealedCards = revealedCards;
        this.text = text;
    }

    @Override
    public List<Choice> getChoices() {
        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice(1, "Discard " + revealedCards.get(0).getName() + " and " + revealedCards.get(1).getName()));
        choices.add(new Choice(2, "Discard " + revealedCards.get(0).getName() + " and " + revealedCards.get(2).getName()));
        choices.add(new Choice(3, "Discard " + revealedCards.get(1).getName() + " and " + revealedCards.get(2).getName()));

        return choices;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return false;
    }

    @Override
    public boolean processAction(Player player) {
        return true;
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        List<Card> discardedCards = new ArrayList<>();

        Integer choice = result.getChoiceSelected();

        if (choice == 1) {
            discardedCards.add(revealedCards.get(0));
            discardedCards.add(revealedCards.get(1));
        } else if (choice == 2) {
            discardedCards.add(revealedCards.get(0));
            discardedCards.add(revealedCards.get(2));
        } else {
            discardedCards.add(revealedCards.get(1));
            discardedCards.add(revealedCards.get(2));
        }

        for (Card discardedCard : discardedCards) {
            revealedCards.remove(discardedCard);
            player.getOpponent().removeCardFromDeck(discardedCard);
            player.getOpponent().addCardToDiscard(discardedCard);
            revealedCards.remove(discardedCard);
            player.addGameLog("Discarded " + discardedCard.getName() + " from opponent's deck");
        }

        Card cardToPutBack = revealedCards.get(0);
        player.getOpponent().addCardToTopOfDeck(cardToPutBack);
        player.addGameLog("Put " + cardToPutBack.getName() + " back on top of opponent's deck");

        return true;
    }
}
