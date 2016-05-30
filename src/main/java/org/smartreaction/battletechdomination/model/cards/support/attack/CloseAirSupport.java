package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class CloseAirSupport extends SupportAttack implements SupportCardAction {
    public CloseAirSupport() {
        name = "Close Air Support";
        cardText = "Discard a card. If you discard a... Resource Card, your opponent must damage a unit; Unit Card, your opponent must damage a Mech; Support Card, your opponent must damage a unit and gain a Heavy Casualties card.";
        industryCost = 5;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, cardText));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is discarding a card for Close Air Support");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        player.discardCardFromHand(selectedCard);
        if (selectedCard instanceof Resource) {
            player.addOpponentAction(new DamageUnit());
        } else if (selectedCard instanceof Unit) {
            player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
        } else if (selectedCard instanceof Support) {
            player.addOpponentAction(new DamageUnit());
            player.getOpponent().gainHeavyCasualties();
        }
    }
}
