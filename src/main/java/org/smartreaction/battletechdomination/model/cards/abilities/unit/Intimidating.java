package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.ReturnOpponentUnitToHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class Intimidating extends UnitAbility implements UnitDeployedAbility {
    //Intimidating: When you deploy this unit, your opponent must return a unit from their deployment zone to their hand (your choice).

    public Intimidating(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addAction(new ReturnOpponentUnitToHand("Choose an opponent's unit to return to opponent's hand"));
    }
}
