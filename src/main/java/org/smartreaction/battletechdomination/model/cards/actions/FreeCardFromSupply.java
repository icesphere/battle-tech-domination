package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.players.Player;

public class FreeCardFromSupply extends Action {

    public FreeCardFromSupply(String text) {
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY);
    }

    @Override
    public boolean processAction(Player player) {
        if (player.getGame().getSupplyGrid().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is choosing a free card from the supply");
            return true;
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        Card card = result.getSelectedCard();
        player.getGame().getSupplyGrid().remove(card);
        player.getGame().addCardToSupplyGrid();
        player.addGameLog(player.getPlayerName() + " acquired a free card from the supply: " + card.getName());
        player.cardAcquired(card);
    }
}
