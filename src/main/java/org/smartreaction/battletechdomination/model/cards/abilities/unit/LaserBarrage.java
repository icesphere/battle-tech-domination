package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class LaserBarrage extends UnitAbility {
    //LASER BARRAGE: At the start of your Combat phase, reveal the top card of your deck. If it is a Unit card, +2 Attack.

    public LaserBarrage(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        Card card = player.revealTopCardOfDeck();
        if (card != null) {
            if (card.isUnit()) {
                player.addGameLog(player.getPlayerName() + "'s " + unit.getName() + " gains +2 Attack.");
                unit.setBonusAttack(2);
            }
        }
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
