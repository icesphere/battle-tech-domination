package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

import java.util.*;

public class ComStarEngineers extends Support {
    private int choicesMade = 0;

    private Set<Integer> differentChoices = new HashSet<>();

    public ComStarEngineers() {
        name = "Com Star Engineers";
        cardText = "Choose three (must be different): +2 Cards; +2 Actions; +3 Industry; +1 Los Tech.";
        industryCost = 4;
        losTechCost = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        choseOne(player);
    }

    private void choseOne(Player player) {
        List<Choice> choices = new ArrayList<>();

        if (!differentChoices.contains(1)) {
            choices.add(new Choice(1, "+2 Cards"));
        }
        if (!differentChoices.contains(2)) {
            choices.add(new Choice(2, "+2 Actions"));
        }
        if (!differentChoices.contains(3)) {
            choices.add(new Choice(3, "+3 Industry"));
        }
        if (!differentChoices.contains(4)) {
            choices.add(new Choice(4, "+1 Los Tech"));
        }


        player.makeChoice(this, "Choose three (must be different)", choices.toArray(new Choice[choices.size()]));
    }

    @Override
    public void choiceMade(int choice, Player player) {
        if (choice == 1) {
            player.addGameLog("Chose +2 Cards");
            player.drawCards(2);
        } else if (choice == 2) {
            player.addGameLog("Chose +2 Actions");
            player.addActions(2);
        } else if (choice == 3) {
            player.addGameLog("Chose +3 Industry");
            player.addIndustry(3);
        } else if (choice == 4) {
            player.addGameLog("Chose +1 Los Tech");
            player.addLosTech(1);
        }

        choicesMade++;

        if (choicesMade < 3) {
            differentChoices.add(choice);
            choseOne(player);
        } else {
            choicesMade = 0;
            differentChoices.clear();
        }
    }
}
