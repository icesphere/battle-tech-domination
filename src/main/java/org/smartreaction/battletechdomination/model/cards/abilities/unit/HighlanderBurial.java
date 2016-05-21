package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapOpponentUnitMaxCost;
import org.smartreaction.battletechdomination.model.players.Player;

public class HighlanderBurial extends UnitAbility {
    //HIGHLANDER BURIAL: You may damage this unit at the start of your Combat phase. If you do, choose an opposing unit costing 5 Industry or less for your opponent to scrap.

    public HighlanderBurial(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        player.cardDamaged(unit);
        player.addGameLog(player.getPlayerName() + " gets to scrap an opponent's unit costing 5 Industry or less due to damaging " + unit.getName() + " with Highlander Burial ability");
        player.addAction(new ScrapOpponentUnitMaxCost(5, "Scrap an opponent's unit costing 5 Industry or less"));
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return player.getTurnPhase() == TurnPhase.COMBAT_START;
    }
}
