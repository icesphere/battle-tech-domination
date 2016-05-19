package org.smartreaction.battletechdomination.model.cards.abilities.unit;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class ReconInForce extends UnitAbility implements UnitDeployedAbility, UnitChoiceAbility {
    //RECON-IN-FORCE: When you deploy this unit, you may discard a card. If you do, your opponent must gain a Raided Supplies card.

    public ReconInForce(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getHandSize() >= 1) {
            player.makeYesNoUnitAbilityChoice(this, "Discard a card to make opponent gain a Raided Supplies card?");
        }
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog(player.getPlayerName() + " chose to use Recon In Force ability on " + unit.getName() + " to discard a card to make opponent gain a Raided Supplies");
            player.discardCardsFromHand(1);
            player.getOpponent().gainRaidedSupplies();
        }
    }
}
