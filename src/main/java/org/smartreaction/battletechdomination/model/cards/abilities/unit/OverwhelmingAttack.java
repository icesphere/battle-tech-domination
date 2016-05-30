package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageOpponentUnitMaxCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class OverwhelmingAttack extends UnitAbility implements UnitDeployedAbility {
    //OVERWHELMING ATTACK: When you deploy this unit, your opponent damages a unit costing 5 Industry or less of your choice.

    public OverwhelmingAttack(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new DamageOpponentUnitMaxCost(5, "Damage an opponent's unit costing 5 Industry or less"));
    }
}
