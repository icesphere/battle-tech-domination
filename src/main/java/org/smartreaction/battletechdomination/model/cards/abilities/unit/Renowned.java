package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Renowned extends UnitAbility implements UnitDeployedAbility {
    //RENOWNED: +1 Card when you deploy this unit.

    public Renowned(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.drawCards(1);
    }
}
