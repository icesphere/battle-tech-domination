package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Quartermaster extends Support {
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
        Choice choice1 = new Choice(1, "+2 Cards");
        Choice choice2 = new Choice(2, "+2 Actions");
        Choice choice3 = new Choice(3, "+2 Industry");
        Choice choice4 = new Choice(1, "+1 Card, +1 Action");
        Choice choice5 = new Choice(1, "+1 Card, +1 Industry");
        Choice choice6 = new Choice(2, "+1 Action, +1 Industry");

        player.makeChoice(this, "Choose one", choice1, choice2, choice3, choice4, choice5, choice6);
    }

    @Override
    public void choiceMade(int choice, Player player) {
        if (choice == 1) {
            player.getGame().gameLog("Chose +2 Cards");
            player.drawCards(2);
        } else if (choice == 2) {
            player.getGame().gameLog("Chose +2 Actions");
            player.addActions(2);
        } else if (choice == 3) {
            player.getGame().gameLog("Chose +2 Industry");
            player.addIndustry(2);
        } else if (choice == 4) {
            player.getGame().gameLog("+1 Card, +1 Action");
            player.drawCards(1);
            player.addActions(1);
        } else if (choice == 5) {
            player.getGame().gameLog("+1 Card, +1 Industry");
            player.drawCards(1);
            player.addIndustry(1);
        } else if (choice == 6) {
            player.getGame().gameLog("+1 Action, +1 Industry");
            player.addActions(1);
            player.addIndustry(1);
        }
    }
}
