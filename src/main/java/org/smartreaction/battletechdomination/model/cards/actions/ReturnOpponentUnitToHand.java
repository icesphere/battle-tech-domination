package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class ReturnOpponentUnitToHand extends Action {
    private CardType cardType;

    public ReturnOpponentUnitToHand(String text) {
        this.text = text;
    }

    public ReturnOpponentUnitToHand(CardType cardType, String text) {
        this.cardType = cardType;
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_OPPONENT_UNITS) && card.isUnit() && (cardType == null || cardType == card.getCardType());
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getOpponent().getNumMechUnitsInDeploymentZone() == 0) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is choosing an opponent's unit to return to opponent's hand");
            return true;
        }
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        Unit unit = (Unit) result.getSelectedCard();
        player.getOpponent().getDeploymentZone().remove(unit);
        player.getOpponent().cardRemovedFromPlay(unit);
        player.getOpponent().addCardToHand(unit);
        return true;
    }
}
