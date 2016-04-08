package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class ScrapForwardBaseOnOverrun extends Action {
    private int difference;

    public ScrapForwardBaseOnOverrun(int difference, String text) {
        this.difference = difference;
        this.text = text;
    }

    public int getDifference() {
        return difference;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return false;
    }
}
