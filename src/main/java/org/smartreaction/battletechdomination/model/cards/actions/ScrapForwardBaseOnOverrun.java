package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Overrun;
import org.smartreaction.battletechdomination.model.players.Player;

public class ScrapForwardBaseOnOverrun extends Action {
    private Overrun overrunCard;

    public ScrapForwardBaseOnOverrun(Overrun overrunCard, String text) {
        this.overrunCard = overrunCard;
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return false;
    }

    @Override
    public boolean processAction(Player player) {
        return true;
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        if (result.getChoiceSelected() == 1) {
            player.addGameLog(player.getPlayerName() + " scrapped Forward Base");
            player.setForwardBaseInDeploymentZone(false);
        } else {
            player.gainOverrunCard(overrunCard);
        }
    }
}
