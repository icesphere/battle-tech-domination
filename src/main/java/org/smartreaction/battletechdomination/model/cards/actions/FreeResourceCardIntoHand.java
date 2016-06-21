package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.cards.resource.AdvancedFactory;
import org.smartreaction.battletechdomination.model.cards.resource.HeavyFactory;
import org.smartreaction.battletechdomination.model.cards.resource.SupplyDrop;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class FreeResourceCardIntoHand extends Action {
    private Integer maxCost;

    public FreeResourceCardIntoHand(Integer maxCost, String text) {
        this.maxCost = maxCost;
        this.text = text;
    }

    public int getMaxCost() {
        return maxCost;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        return cardLocation.equals(Card.CARD_LOCATION_SUPPLY) && card.isResource() && (maxCost == null || card.getIndustryCost() <= maxCost);
    }

    @Override
    public boolean processAction(Player player) {
        List<Card> resourceCards = new ArrayList<>();
        if (!player.getGame().getAdvancedFactories().isEmpty()) {
            resourceCards.add(new AdvancedFactory());
        }
        if (!player.getGame().getSupplyDrops().isEmpty()) {
            resourceCards.add(new SupplyDrop());
        }
        if (!player.getGame().getHeavyFactories().isEmpty()) {
            resourceCards.add(new HeavyFactory());
        }

        for (Card card : player.getGame().getSupply()) {
            if (card instanceof Resource) {
                resourceCards.add(card);
            }
        }

        if (resourceCards.isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is gaining a free resouce card into their hand");
            return true;
        }
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        Card card = result.getSelectedCard();

        if (!(card instanceof AdvancedFactory || card instanceof SupplyDrop || card instanceof HeavyFactory)) {
            player.getGame().getSupplyGrid().remove(card);
            player.getGame().addCardToSupplyGrid();
        }

        player.addGameLog(player.getPlayerName() + " acquired free Resource card into hand: " + card.getName());
        player.addCardToHand(card);
    }
}
