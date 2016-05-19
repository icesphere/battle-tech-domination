package org.smartreaction.battletechdomination.model.cards.abilities;

import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

public class ReconInForce extends Ability implements UnitDeployedAbility {
    //RECON-IN-FORCE: When you deploy this unit, you may discard a card. If you do, your opponent must gain a Raided Supplies card.

    public ReconInForce(Unit unit) {
        super(unit);
    }

    @Override
    public void useAbility(Player player) {
        if (player.getHandSize() >= 1) {
            player.makeYesNoAbilityChoice(unit, "ReconInForce", "Discard a card to make opponent gain a Raided Supplies card?");
        }
    }
}
