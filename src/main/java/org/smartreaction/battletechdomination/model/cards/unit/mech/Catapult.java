package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.HeavyFireSupport;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class Catapult extends MechUnit {
    HeavyFireSupport heavyFireSupport;

    public Catapult() {
        name = "Catapult";
        subName = "CPLT-C1";
        cardText = "HEAVY FIRE SUPPORT: You may discard a Unit card from your hand at the start of your Combat phase. If you do, your opponent must damage a unit.";
        attack = 0;
        defense = 2;
        industryCost = 5;

        heavyFireSupport = new HeavyFireSupport(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return heavyFireSupport.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        heavyFireSupport.useAbility(player);
    }

    @Override
    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return heavyFireSupport.isActionableForCardAction(cardAction, cardLocation, player);
    }

    @Override
    public boolean processCardAction(Player player) {
        return heavyFireSupport.processCardAction(player);
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        heavyFireSupport.processCardActionResult(cardAction, player, result);
    }
}
