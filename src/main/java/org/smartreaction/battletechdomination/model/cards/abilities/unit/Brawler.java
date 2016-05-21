package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.DamageUnit;
import org.smartreaction.battletechdomination.model.players.Player;

public class Brawler extends UnitAbility implements UnitDamagedAbility {
    //BRAWLER: When this unit is damaged, your opponent must damage a unit.

    public Brawler(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.addGameLog(player.getOpponent().getPlayerName() + " must damage a unit due to Brawler ability on " + player.getPlayerName() + "'s " + unit.getName());
        player.addOpponentAction(new DamageUnit());
    }
}
