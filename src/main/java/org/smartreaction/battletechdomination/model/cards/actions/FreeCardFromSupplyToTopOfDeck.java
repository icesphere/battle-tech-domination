package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class FreeCardFromSupplyToTopOfDeck extends Action {
    private Integer maxCost;

    public FreeCardFromSupplyToTopOfDeck(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public Integer getMaxCost() {
        return maxCost;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY) && (maxCost == null || (card.getIndustryCost() <= maxCost && (player.isIgnoreLosTechCost() || card.getLosTechCost() == 0)));
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getGame().getSupplyGrid().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is choosing a free card from the supply to put on top of their deck");
            return true;
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        Card card = result.getSelectedCard();
        player.getGame().getSupplyGrid().remove(card);
        player.getGame().addCardToSupplyGrid();
        player.addGameLog(player.getPlayerName() + " gained free card from supply and put it on top of deck: " + card.getName());
        player.addCardToTopOfDeck(card);
    }
}
