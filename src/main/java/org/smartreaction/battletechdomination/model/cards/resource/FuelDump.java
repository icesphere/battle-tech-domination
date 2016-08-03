package org.smartreaction.battletechdomination.model.cards.resource;

import org.smartreaction.battletechdomination.model.cards.CardType;
import org.smartreaction.battletechdomination.model.cards.Resource;
import org.smartreaction.battletechdomination.model.cards.abilities.support.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.actions.ScrapCardsFromHand;
import org.smartreaction.battletechdomination.model.players.Player;

public class FuelDump extends Resource implements SupportActionChoice {
    public FuelDump() {
        name = "Fuel Dump";
        cardText = "You may scrap a Resource card from your hand.";
        industryCost = 3;
        imageFile = "FuelDup.png";
    }

    @Override
    public void cardPlayed(Player player) {
        player.makeYesNoSupportActionChoice(this, "Do you want to scrap a Resource card from your hand?");
    }

    @Override
    public void supportActionChoiceMade(Player player, int choice) {
        if (choice == 1) {
            player.addAction(new ScrapCardsFromHand(1, "Scrap a Resource card from your hand", true, CardType.RESOURCE));
        }
    }
}
