package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportScrapCardsForBenefit;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapCardsFromHandForBenefit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class PrimusOfComstar extends Support implements SupportActionChoice, SupportScrapCardsForBenefit {
    public PrimusOfComstar() {
        name = "Primus of Comstar";
        cardText = "+2 Actions. Scrap up to 2 Resource cards from your hand. Draw a card for each card scrapped in this way.";
        industryCost = 6;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(2);
        long numResourceCardsInHand = player.getHand().stream().filter(Card::isResource).count();
        if (numResourceCardsInHand > 0) {
            List<Choice> choices = new ArrayList<>(3);
            choices.add(new Choice(0, "Do not scrap cards"));
            choices.add(new Choice(1, "Scrap 1 Resource card"));
            if (numResourceCardsInHand > 1) {
                choices.add(new Choice(2, "Scrap 2 Resource cards"));
            }
            player.makeSupportActionChoice(this, "Scrap up to 2 Resource cards from your hand and draw a card for each card scrapped.", choices.toArray(new Choice[choices.size()]));
        }
    }

    @Override
    public void supportActionChoiceMade(Player player, int choice) {
        if (choice > 0) {
            String cardText = choice == 1 ? "card" : "cards";
            player.addAction(new ScrapCardsFromHandForBenefit(this, choice, "Scrap " + choice + " " + cardText + " from your hand to draw a card for each card scrapped.", CardType.RESOURCE));
        }
    }

    @Override
    public void cardsScrapped(Player player, List<Card> scrappedCards) {
        player.drawCards(scrappedCards.size());
    }
}
