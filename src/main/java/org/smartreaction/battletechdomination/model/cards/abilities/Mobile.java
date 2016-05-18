package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class Mobile extends Ability {
    //MOBILE: +1 Action when you deploy this unit.

    public Mobile(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addActions(1);
    }
}
