package org.smartreaction.battletechdomination.model.cards.actions;

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

import java.util.List;
import java.util.Optional;

public class CardAction extends Action {
    private Card card;

    private List<Card> selectedCards;

    public CardAction(Card card, String text) {
        this.card = card;
        this.text = text;
    }

    public Card getCard() {
        return card;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }

    @Override
    public boolean isCardActionable(Card card, String cardLocation, Player player) {
        if (card instanceof BattlefieldSalvage) {
            if (card.isUnit() && (cardLocation.equals(Card.CARD_LOCATION_HAND) || cardLocation.equals(Card.CARD_LOCATION_PLAYER_UNITS))) {
                return true;
            }
        } else if (card instanceof HiddenBase) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof Refinery) {
            if (card.isResource() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof CloseAirSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof LongTomBattery) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof HeavyFireSupport) {
            if (card.isUnit() && cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof MobileFireSupport) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                return true;
            }
        } else if (card instanceof QuadERPPCs || card instanceof RaidedSupplies) {
            if (cardLocation.equals(Card.CARD_LOCATION_HAND)) {
                if (!getSelectedCards().contains(card)) {
                    return true;
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean processAction(Player player) {
        if (card instanceof HiddenBase || card instanceof Refinery || card instanceof MobileFireSupport || card instanceof CloseAirSupport) {
            if (player.getHand().isEmpty()) {
                return false;
            } else {
                if (card instanceof HiddenBase) {
                    player.addGameLog(player.getPlayerName() + " is setting aside a card for Hidden Base");
                } else if (card instanceof Refinery) {
                    player.addGameLog(player.getPlayerName() + " is gaining a card from Refinery");
                } else if (card instanceof MobileFireSupport) {
                    player.addGameLog(player.getPlayerName() + " is discarding a card to get +1 Attack");
                } else if (card instanceof CloseAirSupport) {
                    player.addGameLog(player.getPlayerName() + " is discarding a card for Close Air Support");
                }
            }
        } else if (card instanceof HeavyFireSupport) {
            if (player.numUnitsInHand() == 0) {
                return false;
            }  else {
                player.addGameLog(player.getPlayerName() + " is discarding a card from their hand to damage an opponent's unit");
            }
        } else if (card instanceof BattlefieldSalvage) {
            if (player.getHand().isEmpty() && player.getDeploymentZone().isEmpty()) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding a card from their hand or deployment zone for BattlefieldSalvage");
            }
        } else if (card instanceof RaidedSupplies) {
            if (player.getHand().size() < 2) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding 2 cards to return Raided Supplies to Overrun pile");
            }
        } else if (card instanceof QuadERPPCs) {
            if (player.getHandSize() < 2) {
                return false;
            } else {
                player.addGameLog(player.getPlayerName() + " is discarding two cards from their hand to make opponent gain a Heavy Casualties card");
            }
        } else if (card instanceof HeavyCasualties) {
            Optional<Card> infantryPlatoonInHand = player.getHand().stream().filter(c -> c instanceof InfantryPlatoon).findAny();
            Optional<Unit> infantryPlatoonInDeploymentZone = player.getDeploymentZone().stream().filter(c -> c instanceof InfantryPlatoon).findAny();

            if (!infantryPlatoonInHand.isPresent() && !infantryPlatoonInDeploymentZone.isPresent()) {
                return false;
            } else if (!infantryPlatoonInHand.isPresent()) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
                player.getDeploymentZone().remove(infantryPlatoonInDeploymentZone.get());
                player.addCardToDiscard(infantryPlatoonInDeploymentZone.get());
                player.getHand().remove(card);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) card);
            } else if (!infantryPlatoonInDeploymentZone.isPresent()) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
                player.discardCardFromHand(infantryPlatoonInHand.get());
                player.getHand().remove(card);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) card);
            }
        } else if (card instanceof CriticalHit) {
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
        if (card instanceof HiddenBase) {
            player.getHand().remove(selectedCard);
            player.getHiddenBaseCards().add(selectedCard);
        } else if (card instanceof Refinery) {
            player.scrapCardFromHand(selectedCard);
            player.gainFreeResourceCardIntoHand(selectedCard.getIndustryCost() + 3);
        } else if (card instanceof MobileFireSupport) {
            player.discardCardFromHand(selectedCard);
            player.addAttack(1);
        } else if (card instanceof HeavyFireSupport) {
            player.discardCardFromHand(selectedCard);
            player.addOpponentAction(new DamageUnit());
        } else if (card instanceof CloseAirSupport) {
            player.discardCardFromHand(selectedCard);
            if (selectedCard instanceof Resource) {
                player.addOpponentAction(new DamageUnit());
            } else if (selectedCard instanceof Unit) {
                player.addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
            } else if (selectedCard instanceof Support) {
                player.addOpponentAction(new DamageUnit());
                player.getOpponent().gainHeavyCasualties();
            }
        } else if (card instanceof LongTomBattery) {
            player.discardCardFromHand(selectedCard);
            if (selectedCard instanceof Unit) {
                player.getOpponent().gainRaidedSupplies();
            } else if (selectedCard instanceof Resource || selectedCard instanceof Support) {
                player.addOpponentAction(new DamageUnit());
            }
        } else if (card instanceof QuadERPPCs) {
            selectedCards.stream().forEach(player::discardCardFromHand);
            player.getOpponent().gainHeavyCasualties();
        } else if (card instanceof BattlefieldSalvage) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.discardCardFromHand(selectedCard);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.discardCardFromDeploymentZone((Unit) selectedCard);
            }
            player.addIndustry(selectedCard.getIndustryCost());
        } else if (card instanceof RaidedSupplies) {
            selectedCards.stream().forEach(player::discardCardFromHand);
            player.getHand().remove(card);
            player.getGame().getRaidedSupplies().add((RaidedSupplies) card);
        } else if (card instanceof HeavyCasualties) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their hand to return a Heavy Casualties back to Overrun pile");
                player.discardCardFromHand(selectedCard);
                player.getHand().remove(card);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) card);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.addGameLog(player.getPlayerName() + " discarded an Infantry Platoon from their deployment zone to return a Heavy Casualties back to Overrun pile");
                player.getDeploymentZone().remove(selectedCard);
                player.addCardToDiscard(selectedCard);
                player.getHand().remove(card);
                player.getGame().getHeavyCasualties().add((HeavyCasualties) card);
            }
        } else if (card instanceof CriticalHit) {
            if (result.getCardLocation().equals(Card.CARD_LOCATION_HAND)) {
                player.addGameLog(player.getPlayerName() + " scrapped a Mech from their hand to return a Critical Hit back to Overrun pile");
                player.scrapCardFromHand(selectedCard);
                player.getHand().remove(card);
                player.getGame().getCriticalHits().add((CriticalHit) card);
            } else if (result.getCardLocation().equals(Card.CARD_LOCATION_PLAYER_UNITS)) {
                player.addGameLog(player.getPlayerName() + " scrapped a Mech from their deployment zone to return a Critical Hit back to Overrun pile");
                player.scrapUnitFromDeploymentZone((Unit) selectedCard);
                player.getHand().remove(card);
                player.getGame().getCriticalHits().add((CriticalHit) card);
            }
        }
    }
}
