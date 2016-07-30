package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ScrapOpponentUnitMaxCost extends Action {
    private int maxCost;

    public ScrapOpponentUnitMaxCost(int maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_OPPONENT_UNITS) && card.getIndustryCost() <= maxCost;
    }

    @Override
    public boolean processAction(Player player) {
        List<Unit> units = player.getOpponent().getDeploymentZone().stream().filter(u -> u.getIndustryCost() <= maxCost).collect(toList());
        if (units.isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is scrapping an opponent's Unit of cost " + maxCost + " Industry or less");
            return true;
        }
    }

    @Override
    public boolean processActionResult(Player player, ActionResult result) {
        player.getOpponent().scrapUnitFromDeploymentZone((Unit) result.getSelectedCard());
        return true;
    }
}
