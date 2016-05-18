package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public interface CardActionAbility {

    boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player);

    boolean processCardAction(Player player);

    void processCardActionResult(CardAction cardAction, Player player, ActionResult result);
}
