package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.BaseSupply;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class MunitionsFactory extends Resource implements BaseSupply, SupportActionChoice {
    public MunitionsFactory() {
        name = "Munitions Factory";
        cardText = "Choose one: +2 Industry or +1 Los Tech";
        industryCost = 3;
        imageFile = "munitions_factory.jpg";
    }

    @Override
    public void cardPlayed(Player player) {
        Choice choice1 = new Choice(1, "+2 Industry");
        Choice choice2 = new Choice(2, "+1 Los Tech");

        player.makeSupportActionChoice(this, "Choose one", choice1, choice2);
    }

    @Override
    public void supportActionChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog("Chose +2 Industry");
            player.addIndustry(2);
        } else if (choice == 2) {
            player.addGameLog("Chose +1 Los Tech");
            player.addLosTech(1);
        }
    }
}
