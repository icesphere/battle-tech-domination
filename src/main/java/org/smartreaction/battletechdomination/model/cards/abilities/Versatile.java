package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class Versatile extends Ability {
    //VERSATILE: When you deploy this unit, choose one: +1 Card; +1 Action; +1 Industry

    public Versatile(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        Choice choice1 = new Choice(1, "+1 Card");
        Choice choice2 = new Choice(2, "+1 Action");
        Choice choice3 = new Choice(3, "+1 Industry");

        player.makeAbilityChoice(card, "Versatile", "Choose one", choice1, choice2, choice3);
    }
}
