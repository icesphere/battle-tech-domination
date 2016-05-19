package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageOpponentUnitMaxCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class FastAssault extends Ability implements UnitDeployedAbility {
    //FAST ASSAULT: When you deploy this unit, your opponent damages a unit costing 5 Industry or less of your choice.

    public FastAssault(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new DamageOpponentUnitMaxCost(5, "Damage an opponent's unit costing 5 Industry or less"));
    }
}
