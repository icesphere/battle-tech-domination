package org.smartreaction.battletechdomination.model.cards.actions;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.HeavyFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.MobileFireSupport;
import org.smartreaction.battletechdomination.model.cards.abilities.QuadERPPCs;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.support.BattlefieldSalvage;
import org.smartreaction.battletechdomination.model.cards.support.HiddenBase;
import org.smartreaction.battletechdomination.model.cards.support.Refinery;
import org.smartreaction.battletechdomination.model.cards.support.attack.CloseAirSupport;
import org.smartreaction.battletechdomination.model.cards.support.attack.LongTomBattery;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;
import org.smartreaction.battletechdomination.model.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardAction extends Action {
    private Card cardActionCard;

    private List<Card> selectedCards = new ArrayList<>(2);

    public CardAction(Card cardActionCard, String text) {
        this.cardActionCard = cardActionCard;
        this.text = text;
    }

    public Card getCardActionCard() {
        return cardActionCard;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        if (cardActionCard instanceof BattlefieldSalvage) {
            if (card.isUnit() && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS))) {
                return true;
            }
        } else if (cardActionCard instanceof HiddenBase) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof Refinery) {
            if (card.isResource() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof CloseAirSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof LongTomBattery) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof HeavyFireSupport) {
            if (card.isUnit() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof MobileFireSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (cardActionCard instanceof QuadERPPCs || cardActionCard instanceof RaidedSupplies) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                if (!getSelectedCards().contains(card)) {
                    return true;
                }
            }
        } else if (cardActionCard instanceof CriticalHit) {
            if (card instanceof MechUnit && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean processAction(Player player) {
        if (cardActionCard instanceof HiddenBase || cardActionCard instanceof Refinery || cardActionCard instanceof MobileFireSupport || cardActionCard instanceof CloseAirSupport) {
            if (player.getHand().isEmpty()) {
                return false;
            } else {
                if (cardActionCard instanceof HiddenBase) {
                    player.addGameLog(player.getPlayerName() + " is setting aside a card for Hidden Base");
                } else if (cardActionCard instanceof Refinery) {
                    player.addGameLog(player.getPlayerName() + " is gaining a card from Refinery");
                } else if (cardActionCard instanceof MobileFireSupport) {
                    player.addGameLog(player.getPlayerName() + " is discarding a card to get +1 Attack");
                } else if (cardActionCard instanceof CloseAirSupport) {
                    player.addGameLog(player.getPlayerName() + " is discarding a card for Close Air Support");
                }
            }
        } else if (cardActionCard instanceof HeavyFireSupport) {
            if (player.numUnitsInHand() == 0) {
                return false;
            }  else {
                player.addGameLog(player.getPlayerName() + " is discarding a card from their hand to damage an opponent's unit");
            }
        } else if (cardActionCard instanceof BattlefieldSalvage) {
            if (player.getHand().isEmpty() && player.getDeploymentZone().isEmpty()) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding a card from their hand or deployment zone for BattlefieldSalvage");
            }
        } else if (cardActionCard instanceof RaidedSupplies) {
            if (player.getHand().size() < 2) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding 2 cards to return Raided Supplies to Overrun pile");
            }
        } else if (cardActionCard instanceof QuadERPPCs) {
            if (player.getHandSize() < 2) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding 2 cards from their hand to make opponent gain a Heavy Casualties card");
            }
        } else if (cardActionCard instanceof HeavyCasualties) {
            Optional<Card> infantryPlatoonInHand = player.getHand().stream().filter(c -> c instanceof InfantryPlatoon).findAny();
            Optional<Unit> infantryPlatoonInDeploymentZone = player.getDeploymentZone().stream().filter(c -> c instanceof InfantryPlatoon).findAny();

            if (!infantryPlatoonInHand.isPresent() && !infantryPlatoonInDeploymentZone.isPresent()) {
                return false;
            } else if (!infantryPlatoonInHand.isPresent()) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
                player.getDeploymentZone().remove(infantryPlatoonInDeploymentZone.get());
                player.addCardToDiscard(infantryPlatoonInDeploymentZone.get());
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) cardActionCard);
                return false;
            } else if (!infantryPlatoonInDeploymentZone.isPresent()) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
                player.discardCardFromHand(infantryPlatoonInHand.get());
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) cardActionCard);
                return false;
            } else {
                player.makeAbilityChoice(cardActionCard, "HeavyCasualties", "Discard an Infantry Platoon.", new Choice(1, "Discard from hand"), new Choice(2, "Discard from deployment zone"));
                return false;
            }
        } else if (cardActionCard instanceof CriticalHit) {
            int numMechUnitsInDeploymentZone = player.getNumMechUnitsInDeploymentZone();
            long numMechsInHand = player.getHand().stream().filter(c -> c instanceof MechUnit).count();

            if (numMechUnitsInDeploymentZone == 0 && numMechsInHand == 0) {
                return false;
            }
        }  
        
        return true;
    }

    @Override
    public void processActionResult(Player player, ActionResult result) {
        Card selectedCard = result.getSelectedCard();
        if (cardActionCard instanceof HiddenBase) {
            player.getHand().remove(selectedCard);
            player.getHiddenBaseCards().add(selectedCard);
        } else if (cardActionCard instanceof Refinery) {
            player.scrapCardFromHand(selectedCard);
            player.gainFreeResourceCardIntoHand(selectedCard.getIndustryCost() + 3);
        } else if (cardActionCard instanceof MobileFireSupport) {
            player.discardCardFromHand(selectedCard);
            player.addAttack(1);
        } else if (cardActionCard instanceof HeavyFireSupport) {
            player.discardCardFromHand(selectedCard);
            player.addOpponentAction(new DamageUnit());
        } else if (cardActionCard instanceof CloseAirSupport) {
            player.discardCardFromHand(selectedCard);
            if (selectedCard instanceof Resource) {
                player.addOpponentAction(new DamageUnit());
            } else if (selectedCard instanceof Unit) {
                player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
            } else if (selectedCard instanceof Support) {
                player.addOpponentAction(new DamageUnit());
                player.getOpponent().gainHeavyCasualties();
            }
        } else if (cardActionCard instanceof LongTomBattery) {
            player.discardCardFromHand(selectedCard);
            if (selectedCard instanceof Unit) {
                player.getOpponent().gainRaidedSupplies();
            } else if (selectedCard instanceof Resource || selectedCard instanceof Support) {
                player.addOpponentAction(new DamageUnit());
            }
        } else if (cardActionCard instanceof QuadERPPCs) {
            selectedCards.stream().forEach(player::discardCardFromHand);
            player.getOpponent().gainHeavyCasualties();
        } else if (cardActionCard instanceof BattlefieldSalvage) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.discardCardFromHand(selectedCard);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.discardCardFromDeploymentZone((Unit) selectedCard);
            }
            player.addIndustry(selectedCard.getIndustryCost());
        } else if (cardActionCard instanceof RaidedSupplies) {
            selectedCards.stream().forEach(player::discardCardFromHand);
            player.getCardsPlayed().remove(cardActionCard);
            player.getGame().getRaidedSupplies().add((RaidedSupplies) cardActionCard);
        } else if (cardActionCard instanceof HeavyCasualties) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
                player.discardCardFromHand(selectedCard);
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) cardActionCard);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
                player.getDeploymentZone().remove(selectedCard);
                player.addCardToDiscard(selectedCard);
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) cardActionCard);
            }
        } else if (cardActionCard instanceof CriticalHit) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.addGameLog(player.getPlayerName() + " scrapped a Mech from their hand to return a Critical Hit back to Overrun pile");
                player.scrapCardFromHand(selectedCard);
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getCriticalHits().add((CriticalHit) cardActionCard);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.addGameLog(player.getPlayerName() + " scrapped a Mech from their deployment zone to return a Critical Hit back to Overrun pile");
                player.scrapUnitFromDeploymentZone((Unit) selectedCard);
                player.getCardsPlayed().remove(cardActionCard);
                player.getGame().getCriticalHits().add((CriticalHit) cardActionCard);
            }
        }
    }

    @Override
    public boolean showActionDialog() {
        return selectedCards.size() == 0;
    }
}
