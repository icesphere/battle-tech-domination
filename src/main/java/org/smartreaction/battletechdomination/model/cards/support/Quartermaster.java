package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

public class Quartermaster extends Support implements SupportActionChoice {
    public Quartermaster() {
        name = "Quartermaster";
        cardText = "+1 Industry. Choose two (may be same or different): +1 Card; +1 Action; +1 Industry.";
        industryCost = 3;
        imageFile = "Quartermaster.png";
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
        Choice choice4 = new Choice(4, "+1 Card, +1 Action");
        Choice choice5 = new Choice(5, "+1 Card, +1 Industry");
        Choice choice6 = new Choice(6, "+1 Action, +1 Industry");

        player.makeSupportActionChoice(this, "Choose one", choice1, choice2, choice3, choice4, choice5, choice6);
    }

    @Override
    public void supportActionChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addGameLog("Chose +2 Cards");
            player.drawCards(2);
        } else if (choice == 2) {
            player.addGameLog("Chose +2 Actions");
            player.addActions(2);
        } else if (choice == 3) {
            player.addGameLog("Chose +2 Industry");
            player.addIndustry(2);
        } else if (choice == 4) {
            player.addGameLog("+1 Card, +1 Action");
            player.drawCards(1);
            player.addActions(1);
        } else if (choice == 5) {
            player.addGameLog("+1 Card, +1 Industry");
            player.drawCards(1);
            player.addIndustry(1);
        } else if (choice == 6) {
            player.addGameLog("+1 Action, +1 Industry");
            player.addActions(1);
            player.addIndustry(1);
        }
    }
}
