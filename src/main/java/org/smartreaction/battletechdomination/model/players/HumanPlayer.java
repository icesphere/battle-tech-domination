package org.smartreaction.battletechdomination.model.players;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.model.cards.Card;

public class HumanPlayer extends Player {
    private User user;

    public HumanPlayer(User user) {
        this.user = user;
        playerName = user.getUsername();
    }

    @Override
    public void makeChoice(Card card, Choice... choices) {

    }

    @Override
    public void makeAbilityChoice(Card card, String abilityName, Choice... choices) {

    }

    @Override
    public void makeYesNoAbilityChoice(Card card, String abilityName, String text) {

    }

    public User getUser() {
        return user;
    }
}
