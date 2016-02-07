package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Quartermaster extends Support {
    private int choicesMade = 0;

    public Quartermaster() {
        name = "Quartermaster";
        cardText = "+1 Industry. Choose two (may be same or different): +1 Card; +1 Action; +1 Industry.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(1);

        choseOne(player);
    }

    private void choseOne(Player player) {
        Choice choice1 = new Choice(1, "+1 Cards");
        Choice choice2 = new Choice(2, "+1 Actions");
        Choice choice3 = new Choice(3, "+1 Industry");

        player.makeChoice(this, choice1, choice2, choice3);
    }

    @Override
    public void choiceMade(int choice, Player player) {
        if (choice == 1) {
            player.getGame().gameLog("Chose +1 Card1");
            player.drawCards(1);
        } else if (choice == 2) {
            player.getGame().gameLog("Chose +1 Action1");
            player.addActions(1);
        } else if (choice == 3) {
            player.getGame().gameLog("Chose +1 Industry");
            player.addIndustry(1);
        }

        choicesMade++;

        if (choicesMade < 2) {
            choseOne(player);
        } else {
            choicesMade = 0;
        }
    }
}
