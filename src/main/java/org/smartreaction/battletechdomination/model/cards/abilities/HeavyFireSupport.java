package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class HeavyFireSupport extends Ability {
    //HEAVY FIRE SUPPORT: Once per turn during your Action phase, you may discard a Unit card from your hand. If you do, your opponent must damage a unit.

    public HeavyFireSupport(Card card) {
        super(card);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START && player.numUnitsInHand() > 0;
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new CardAction(card, "Discard a Unit card from your hand to make your opponent damage a unit."));
    }

    @Override
    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return card.isUnit() && cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.numUnitsInHand() == 0) {
            return false;
        }  else {
            player.addGameLog(player.getPlayerName() + " is discarding a card from their hand to damage an opponent's unit");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        player.discardCardFromHand(result.getSelectedCard());
        player.addOpponentAction(new DamageUnit());
    }
}
