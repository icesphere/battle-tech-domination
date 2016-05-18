package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Ability {
    protected Card card;

    public Ability(Card card) {
        this.card = card;
    }

    public abstract void useAbility(Player player);

    public Card getCard() {
        return card;
    }

    public boolean isAbilityAvailable(Player player) {
        return false;
    }

    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return false;
    }

    public boolean processCardAction(Player player) {
        return true;
    }

    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {

    }
}
