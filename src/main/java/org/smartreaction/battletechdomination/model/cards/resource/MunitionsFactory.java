package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Resource;

public class MunitionsFactory extends Resource {
    public MunitionsFactory() {
        name = "Munitions Factory";
        cardText = "Choose one: +2 Industry or +1 Los Tech";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        Choice choice1 = new Choice(1, "+2 Industry");
        Choice choice2 = new Choice(2, "+1 Los Tech");

        player.makeChoice(this, choice1, choice2);
    }

    @Override
    public void choiceMade(int choice, Player player) {
        if (choice == 1) {
            player.getGame().gameLog("Chose +2 Industry");
            player.addIndustry(2);
        } else if (choice == 2) {
            player.getGame().gameLog("Chose +1 Los Tech");
            player.addLosTech(1);
        }
    }
}
