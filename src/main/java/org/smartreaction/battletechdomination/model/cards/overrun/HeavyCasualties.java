package org.smartreaction.battletechdomination.model.cards.overrun;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.OverrunSupport;
import org.smartreaction.battletechdomination.model.cards.Unit;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportCardAction;
import org.smartreaction.battletechdomination.model.cards.actions.ActionResult;
import org.smartreaction.battletechdomination.model.cards.actions.CardAction;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.Optional;

public class HeavyCasualties extends OverrunSupport implements SupportActionChoice, SupportCardAction {
    public HeavyCasualties() {
        name = "Heavy Casualties";
        cardText = "You may discard an Infantry Platoon from your hand or deployment zone.  If you do, return this card to the Overrun pile.";
        overrunAmount = 1;
    }

    @Override
    public void cardPlayed(Player player) {
        player.addAction(new CardAction(this, "Discard an Infantry Platoon from Hand or Deployment Zone"));
    }

    @Override
    public void abilityChoiceMade(Player player, int choice) {
        if (choice == 1) {
            Optional<Card> infantryPlatoonInHand = player.getHand().stream().filter(c -> c instanceof InfantryPlatoon).findAny();
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
            player.discardCardFromHand(infantryPlatoonInHand.get());
            player.getCardsPlayed().remove(this);
        } else if (choice == 2) {
            Optional<Unit> infantryPlatoonInDeploymentZone = player.getDeploymentZone().stream().filter(c -> c instanceof InfantryPlatoon).findAny();
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
            player.getDeploymentZone().remove(infantryPlatoonInDeploymentZone.get());
            player.addCardToDiscard(infantryPlatoonInDeploymentZone.get());
            player.getCardsPlayed().remove(this);
        }
    }

    @Override
    public boolean isCardActionable(Card card, CardAction cardAction, String cardLocation, Player player) {
        return false;
    }

    @Override
    public boolean processCardAction(Player player) {Optional<Card> infantryPlatoonInHand = player.getHand().stream().filter(c -> c instanceof InfantryPlatoon).findAny();
        Optional<Unit> infantryPlatoonInDeploymentZone = player.getDeploymentZone().stream().filter(c -> c instanceof InfantryPlatoon).findAny();

        if (!infantryPlatoonInHand.isPresent() && !infantryPlatoonInDeploymentZone.isPresent()) {
            return false;
        } else if (!infantryPlatoonInHand.isPresent()) {
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
            player.getDeploymentZone().remove(infantryPlatoonInDeploymentZone.get());
            player.addCardToDiscard(infantryPlatoonInDeploymentZone.get());
            player.getCardsPlayed().remove(this);
            return false;
        } else if (!infantryPlatoonInDeploymentZone.isPresent()) {
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
            player.discardCardFromHand(infantryPlatoonInHand.get());
            player.getCardsPlayed().remove(this);
            return false;
        } else {
            player.makeSupportActionChoice(this, "Discard an Infantry Platoon.", new Choice(1, "Discard from hand"), new Choice(2, "Discard from deployment zone"));
            return false;
        }
    }

    @Override
    public void processCardActionResult(CardAction cardAction, Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
            player.discardCardFromHand(selectedCard);
            player.getCardsPlayed().remove(this);
        } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
            player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
            player.getDeploymentZone().remove(selectedCard);
            player.addCardToDiscard(selectedCard);
            player.getCardsPlayed().remove(this);
        }
    }
}
