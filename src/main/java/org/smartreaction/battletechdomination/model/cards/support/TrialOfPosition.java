package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.actions.FreeCardFromSupplyToHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class TrialOfPosition extends Support implements SupportCardAction {
    public TrialOfPosition() {
        name = "Trial of Position";
        cardText = "+1 Action. Scrap a Mech from your hand or deployment zone. Gain a Mech from the Supply regardless of its cost and put it in your hand.";
        industryCost = 3;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addActions(1);
        player.addAction(new CardAction(this, "Scrap a Mech from your hand or deployment zone to gain a Mech from the Supply regardless of its cost and put it in your hand."));
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return card.isMechUnit() && (cardLocation.equals(CARD_LOCATION_HAND) || cardLocation.equals(CARD_LOCATION_PLAYER_UNITS));
    }

    @Override
    public boolean processCardAction(Player player) {
        return player.getNumMechUnitsInDeploymentZone() > 0 || player.getNumMechUnitsInHand() > 0;
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
            player.scrapCardFromHand(selectedCard);
        } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            player.scrapUnitFromDeploymentZone((Unit) selectedCard);
        }
        player.addAction(new FreeCardFromSupplyToHand(null, CardType.UNIT_MECH, "Gain a Mech from the Supply regardless of its cost and put it in your hand."));
    }
}
