package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.players.Player;

public class FreeCardFromSupplyToHand extends Action {
    private Integer maxCost;
    private CardType cardType;

    public FreeCardFromSupplyToHand(Integer maxCost, CardType cardType, String text) {
        this.maxCost = maxCost;
        this.cardType = cardType;
        this.text = text;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY) && (cardType == null || cardType == card.getCardType()) && (maxCost == null || (card.getIndustryCost() <= maxCost && (player.isIgnoreLosTechCost() || card.getLosTechCost() == 0)));
    }

    @Override
    public boolean processAction(Player player) {
        return !player.getGame().getSupplyGrid().isEmpty();
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        Card card = result.getSelectedCard();
        player.getGame().getSupplyGrid().remove(card);
        player.getGame().addCardToSupplyGrid();
        player.addGameLog(player.getPlayerName() + " gained free card from supply and put it in their hand: " + card.getName());
        player.addCardToHand(card);
    }
}
