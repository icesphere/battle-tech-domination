package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyCasualties extends OverrunSupport implements SupportCardAction {
    public HeavyCasualties() {
        name = "Heavy Casualties";
        cardText = "You may discard a Unit card from your hand or deployment zone.  If you do, scrap this card.";
        overrunAmount = 1;
        imageFile = "HeavyCasualties.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Discard a Unit card from your Hand or Deployment Zone to scrap Heavy Casualties"));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return card.isUnit() && (CARD_LOCATION_HAND.equals(cardLocation) || CARD_LOCATION_PLAYER_UNITS.equals(cardLocation));
    }

    @Override
    public boolean processCardAction(Player player) {
        int numUnitsInDeploymentZone = player.getNumUnitsInDeploymentZone();
        long numUnitsInHand = player.getHand().stream().filter(Card::isUnit).count();

        return numUnitsInDeploymentZone > 0 || numUnitsInHand > 0;
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
            player.addGameLog(player.getPlayerName() + " discarded " + selectedCard.getName() + " from their hand to scrap a Heavy Casualties");
            player.discardCardFromHand(selectedCard);
            player.getCardsPlayed().remove(this);
        } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            player.addGameLog(player.getPlayerName() + " discarded " +  selectedCard.getName() + " from their deployment zone to scrap a Heavy Casualties");
            player.discardCardFromDeploymentZone((Unit) selectedCard);
            player.getCardsPlayed().remove(this);
        }
    }
}
