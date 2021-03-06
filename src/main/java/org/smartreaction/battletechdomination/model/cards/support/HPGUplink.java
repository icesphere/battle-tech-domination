package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.players.Player;
import org.smartreaction.battletechdomination.model.cards.Support;

import java.util.*;

public class HPGUplink extends Support implements SupportActionChoice {
    private int choicesMade = 0;

    private Set<Integer> differentChoices = new HashSet<>();

    public HPGUplink() {
        name = "HPG Uplink";
        cardText = "Choose three (must be different): +2 Cards; +2 Actions; +3 Industry; +1 Los Tech.";
        industryCost = 4;
        losTechCost = 1;
        imageFile = "HPGUplink.png";
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

        String text;

        if (differentChoices.size() == 1) {
            text = "Choose two more (must be different)";
        } else if (differentChoices.size() == 2) {
            text = "Choose one more";
        } else {
            text = "Choose three (must be different)";
        }

        player.makeSupportActionChoice(this, text, choices.toArray(new Choice[choices.size()]));
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
