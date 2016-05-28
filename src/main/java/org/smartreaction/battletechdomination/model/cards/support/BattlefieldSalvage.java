package org.smartreaction.battletechdomination.model.cards.support;

import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.Support;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.players.Player;

public class BattlefieldSalvage extends Support implements SupportActionChoice, SupportCardAction {
    public BattlefieldSalvage() {
        name = "Battlefield Salvage";
        cardText = "+2 Industry. You may discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded.";
        industryCost = 3;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addIndustry(2);
        player.makeYesNoSupportActionChoice(this, "Do you want to discard a Unit card from your hand or deployment zone to gain an additional +X Industry, where X is the Industry cost of the card you discard?");
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addAction(new CardAction(this, "Discard a Unit card from your hand or deployment zone. Gain an additional +X Industry, where X is the Industry cost of the card you discarded."));
        }
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return card.isUnit() && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS));
    }

    @Override
    public boolean processCardAction(Player player) {
        if (player.getHand().isEmpty() && player.getDeploymentZone().isEmpty()) {
            return false;
        } else {
            player.addGameLog(player.getPlayerName() + " is discarding a card from their hand or deployment zone for BattlefieldSalvage");
            return true;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
            player.discardCardFromHand(selectedCard);
        } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            player.discardCardFromDeploymentZone((Unit) selectedCard);
        }
        player.addIndustry(selectedCard.getIndustryCost());
    }
}
