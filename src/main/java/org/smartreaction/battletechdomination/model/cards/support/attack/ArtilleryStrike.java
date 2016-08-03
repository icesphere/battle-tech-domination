package org.smartreaction.battletechdomination.model.cards.support.attack;

import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class ArtilleryStrike extends SupportAttack implements SupportCardAction {
    public ArtilleryStrike() {
        name = "Artillery Strike";
        cardText = "Discard a card. If you discard a... Unit Card, you opponent must gain a Raided Supplies card; Resource or Support Card, your opponent must damage a Unit.";
        industryCost = 5;
        imageFile = "ArtilleryStrike.png";
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
        return !player.getHand().isEmpty();
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        player.discardCardFromHand(selectedCard);
        if (selectedCard instanceof Unit) {
            player.getOpponent().gainRaidedSupplies();
        } else if (selectedCard instanceof Resource || selectedCard instanceof Support) {
            player.addOpponentAction(new DamageUnit());
        }
    }
}
