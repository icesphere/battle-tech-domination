package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class BattlefieldSalvage extends Support implements SupportActionChoice {
    public BattlefieldSalvage() {
        name = "Battlefield Salvage";
        cardText = "+2 Industry. You may discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.makeYesNoSupportActionChoice(this, "Do you want to discard a Unit card from your hand or deployment zone to gain an additional +X Industry, where X is the Industry cost of the card you discard?");
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addAction(new CardAction(this, "Discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded."));
        }
    }
}
