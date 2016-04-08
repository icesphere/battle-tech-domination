package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.abilities.HeavyFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.MobileFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.QuadERPPCs;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.support.BattlefieldSalvage;
import org.smartreaction.battletechdomination.model.cards.support.HiddenBase;
import org.smartreaction.battletechdomination.model.cards.support.Refinery;
import org.smartreaction.battletechdomination.model.cards.support.attack.CloseAirSupport;
import org.smartreaction.battletechdomination.model.cards.support.attack.LongTomBattery;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

public class CardAction extends Action {
    private Card card;

    private List<Card> selectedCards;

    public CardAction(Card card, String text) {
        this.card = card;
        this.text = text;
    }

    public Card getCard() {
        return card;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        if (card instanceof BattlefieldSalvage) {
            if (card.isUnit() && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS))) {
                return true;
            }
        } else if (card instanceof HiddenBase) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof Refinery) {
            if (card.isResource() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof CloseAirSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof LongTomBattery) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof HeavyFireSupport) {
            if (card.isUnit() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof MobileFireSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof QuadERPPCs || card instanceof RaidedSupplies) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                if (!getSelectedCards().contains(card)) {
                    return true;
                }
                return true;
            }
        }

        return false;
    }
}
