package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class AdvancedCommSystem extends UnitAbility implements StartOfTurnAbility {
    //ADVANCED COMM SYSTEM: At the start of your turn, +1 Card if your total Attack Value is greater than your opponent's total Attack Value.

    public AdvancedCommSystem(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getCurrentAttack() > player.getOpponent().getCurrentAttack()) {
            player.drawCards(1);
            player.addGameLog(player.getPlayerName() + " got +1 Card from Advanced Comm System ability on " + unit.getName());
        }
    }
}
