package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DamageUnitMinCost extends Action {
    private int minCost;

    public DamageUnitMinCost(int minCost, String text) {
        this.minCost = minCost;
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS) && card.isUnit() && card.getIndustryCost() >= minCost;
    }

    @Override
    public boolean processAction(Player player) {
        List<Unit> units = player.getDeploymentZone().stream().filter(u -> u.getIndustryCost() >= minCost).collect(toList());
        if (units.isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " must damage a Unit of cost " + minCost + " Industry or more");
            return true;
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        player.cardDamaged((Unit) result.getSelectedCard());
    }
}
