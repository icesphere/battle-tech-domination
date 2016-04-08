package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.players.Player;

public class DamageUnit extends Action {
    private CardType cardType;

    public DamageUnit() {
        this.text = "Damage a Unit";
    }

    public DamageUnit(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS) && (cardType == null || card.getCardType() == cardType);
    }
}
