package org.smartreaction.battletechdomination.model.cards.unit.mech;

import org.smartreaction.battletechdomination.model.cards.MechUnit;
import org.smartreaction.battletechdomination.model.cards.abilities.MobileFireSupport;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class Trebuchet extends MechUnit {
    MobileFireSupport mobileFireSupport;

    public Trebuchet() {
        name = "Trebuchet";
        subName = "TBT-5N";
        cardText = "MOBILE FIRE SUPPORT: At the start of your Combat phase, you may discard a card from your hand. If you do, +1 Attack.";
        attack = 1;
        defense = 1;
        industryCost = 4;

        mobileFireSupport = new MobileFireSupport(this);
    }

    @Override
    public boolean isAbilityAvailable(Player player) {
        return mobileFireSupport.isAbilityAvailable(player);
    }

    @Override
    public void useUnitAbility(Player player) {
        mobileFireSupport.useAbility(player);
    }

    @Override
    public boolean isActionableForCardAction(CardAction cardAction, String cardLocation, Player player) {
        return mobileFireSupport.isActionableForCardAction(cardAction, cardLocation, player);
    }

    @Override
    public boolean processCardAction(Player player) {
        return mobileFireSupport.processCardAction(player);
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        mobileFireSupport.processCardActionResult(cardAction, player, result);
    }
}
