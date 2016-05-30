package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class CardAction extends Action {
    private Card cardActionCard;

    private List<Card> selectedCards = new ArrayList<>(2);

    public CardAction(Card cardActionCard, String text) {
        this.cardActionCard = cardActionCard;
        this.text = text;
    }

    public Card getCardActionCard() {
        return cardActionCard;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        if (cardActionCard instanceof SupportCardAction) {
            return ((SupportCardAction) cardActionCard).isCardActionable(card, this, cardLocation, player);
        } else if (cardActionCard.isUnit()) {
            Unit unit = (Unit) cardActionCard;
            return unit.isActionableForCardAction(card, this, cardLocation, player);
        }

        return false;
    }

    @Override
    public boolean processAction(Player player) {
        if (cardActionCard instanceof SupportCardAction) {
            return ((SupportCardAction) cardActionCard).processCardAction(player);
        } else if (cardActionCard.isUnit()) {
            return ((Unit) cardActionCard).processCardAction(player);
        }
        
        return true;
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        if (cardActionCard instanceof SupportCardAction) {
            ((SupportCardAction) cardActionCard).processCardActionResult(this, player, result);
        } else if (cardActionCard.isUnit()) {
            ((Unit) cardActionCard).processCardActionResult(this, player, result);
        }
    }

    @Override
    public boolean showActionDialog() {
        return selectedCards.size() == 0;
    }
}
