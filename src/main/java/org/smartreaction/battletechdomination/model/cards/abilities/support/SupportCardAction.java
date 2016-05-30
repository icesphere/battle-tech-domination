package org.smartreaction.battletechdomination.model.cards.abilities.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public interface SupportCardAction {
    boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player);

    boolean processCardAction(Player player);

    void processCardActionResult(CardAction cardAction, Player player, ActionResult result);
}
