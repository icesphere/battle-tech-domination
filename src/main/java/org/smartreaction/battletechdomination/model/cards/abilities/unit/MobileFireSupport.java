package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class MobileFireSupport extends UnitAbility implements CardActionAbility {
    //MOBILE FIRE SUPPORT: At the start of your Combat phase, you may discard a card from your hand. If you do, +1 Attack.

    public MobileFireSupport(Unit unit) {
        super(unit);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START && player.getHandSize() > 0;
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new CardAction(unit, "Discard a card from your hand for +1 Attack."));
    }

    @Override
    public boolean isActionableForCardAction(Card card, CardAction cardAction, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_HAND);
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is discarding a card to get +1 Attack");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        player.discardCardFromHand(result.getSelectedCard());
        player.addAttack(1);
    }
}
