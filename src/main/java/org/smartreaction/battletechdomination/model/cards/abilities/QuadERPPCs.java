package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class QuadERPPCs extends Ability implements CardActionAbility {
    //QUAD ER PPCs: You may discard two cards from your hand at the start of your Combat phase. If you do, your opponent gains a Heavy Casualties card.

    public QuadERPPCs(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new CardAction(card, "Discard 2 cards from your hand to make your opponent gain a Heavy Casualties card."));
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START && player.getHandSize() >= 2;
    }

    @Override
    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
            if (!cardAction.getSelectedCards().contains(card)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHandSize() < 2) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is discarding 2 cards from their hand to make opponent gain a Heavy Casualties card");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        cardAction.getSelectedCards().stream().forEach(player::discardCardFromHand);
        player.getOpponent().gainHeavyCasualties();
    }
}
