package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.actions.DamageOpponentUnitMaxCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class FastAssault extends Ability {
    //FAST ASSAULT: When you deploy this unit, your opponent damages a unit costing 5 Industry or less of your choice.

    public FastAssault(Card card) {
        super(card);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new DamageOpponentUnitMaxCost(5, "Damage an opponent's unit costing 5 Industry or less"));
    }
}
