package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public abstract class Ability {
    protected Card card;

    public Ability(Card card) {
        this.card = card;
    }

    public abstract void useAbility(Player player);

    public boolean isAbilityAvailable(Player player) {
        return false;
    }

    public Card getCard() {
        return card;
    }
}
