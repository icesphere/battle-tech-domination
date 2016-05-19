package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnitMinCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class JumpJets extends UnitAbility implements UnitDeployedAbility {
    //JUMP JETS: When you deploy this unit, your opponent must damage a unit costing 6 Industry or more

    public JumpJets(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addOpponentAction(new DamageUnitMinCost(6, "Damage a unit costing 6 Industry or more"));
    }
}
