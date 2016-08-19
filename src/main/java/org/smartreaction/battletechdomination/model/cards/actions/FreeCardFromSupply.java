package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.players.Player;

public class FreeCardFromSupply extends Action {

    private Integer maxCost;

    private String destination = Card.CARD_LOCATION_DISCARD;

    private CardType cardType;

    public FreeCardFromSupply(String text) {
        this.text = text;
    }

    public FreeCardFromSupply(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public FreeCardFromSupply(Integer maxCost, String text, String destination) {
        this.maxCost = maxCost;
        this.text = text;
        this.destination = destination;
    }

    public FreeCardFromSupply(Integer maxCost, String text, String destination, CardType cardType) {
        this.maxCost = maxCost;
        this.text = text;
        this.destination = destination;
        this.cardType = cardType;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY) &&
                (cardType == null || card.getCardType() == cardType) &&
                (maxCost == null ||
                        (card.getIndustryCost() <= maxCost
                                && (player.isIgnoreLosTechCost() || card.getLosTechCost() == 0)));
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
    public boolean processActionResult(Player player, ActionResult result) {
        Card card = result.getSelectedCard();

        player.getGame().getSupplyGrid().remove(card);
        player.getGame().addCardToSupplyGrid();

        if (destination.equals(Card.CARD_LOCATION_HAND)) {
            player.addGameLog(player.getPlayerName() + " gained free card from the supply and put it in their hand: " + card.getName());
            player.addCardToHand(card);
        } else if (destination.equals(Card.CARD_LOCATION_DECK)) {
            player.addGameLog(player.getPlayerName() + " gained free card from the supply and put it on top of deck: " + card.getName());
            player.addCardToTopOfDeck(card);
        } else {
            player.addGameLog(player.getPlayerName() + " gained free card from the supply: " + card.getName());
            player.cardAcquired(card);
        }
        return true;
    }

    @Override
    public boolean isShowDoNotUse() {
        return true;
    }
}
