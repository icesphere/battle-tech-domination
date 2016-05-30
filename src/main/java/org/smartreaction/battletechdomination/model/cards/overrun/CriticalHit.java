package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class CriticalHit extends OverrunSupport implements SupportCardAction {
    public CriticalHit() {
        name = "Critical Hit";
        cardText = "You may scrap a Mech from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Scrap a Mech from your hand or deployment zone to return Critical Hit to Overrun pile"));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return card.isMechUnit() && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS));
    }

    @Override
    public boolean processCardAction(Player player) {
        int numMechUnitsInDeploymentZone = player.getNumMechUnitsInDeploymentZone();
        long numMechsInHand = player.getHand().stream().filter(c -> c instanceof MechUnit).count();

        return numMechUnitsInDeploymentZone > 0 || numMechsInHand > 0;
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
            player.addGameLog(player.getPlayerName() + " scrapped a Mech from their hand to return a Critical Hit back to Overrun pile");
            player.scrapCardFromHand(selectedCard);
            player.getCardsPlayed().remove(this);
        } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            player.addGameLog(player.getPlayerName() + " scrapped a Mech from their deployment zone to return a Critical Hit back to Overrun pile");
            player.scrapUnitFromDeploymentZone((Unit) selectedCard);
            player.getCardsPlayed().remove(this);
        }
    }
}
