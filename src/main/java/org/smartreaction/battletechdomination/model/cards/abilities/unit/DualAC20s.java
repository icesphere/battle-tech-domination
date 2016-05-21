package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class DualAC20s extends UnitAbility implements UnitDeployedAbility {
    //DUAL AC/20s: When you deploy this unit, your opponent must damage a Mech.

    public DualAC20s(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech"));
    }
}
