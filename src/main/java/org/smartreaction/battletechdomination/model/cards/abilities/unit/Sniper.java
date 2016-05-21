package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Sniper extends UnitAbility implements UnitDeployedAbility {
    //SNIPER: When you deploy this unit, your opponent must damage a unit.

    public Sniper(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addOpponentAction(new DamageUnit());
    }
}
