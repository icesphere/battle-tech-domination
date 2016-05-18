package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.QuadERPPCs;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class Masakari extends MechUnit {
    QuadERPPCs quadERPPCs;

    public Masakari() {
        name = "Masakari";
        subName = "WARHAWK";
        cardText = "QUAD ER PPCs: You may discard two cards from your hand at the start of your Combat phase. If you do, your opponent gains a Heavy Casualties card.";
        attack = 4;
        defense = 4;
        industryCost = 8;
        losTechCost = 1;

        quadERPPCs = new QuadERPPCs(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return quadERPPCs.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        quadERPPCs.useAbility(player);
    }

    @Override
    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return quadERPPCs.isActionableForCardAction(cardAction, cardLocation, player);
    }

    @Override
    public boolean processCardAction(Player player) {
        return quadERPPCs.processCardAction(player);
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        quadERPPCs.processCardActionResult(cardAction, player, result);
    }
}
