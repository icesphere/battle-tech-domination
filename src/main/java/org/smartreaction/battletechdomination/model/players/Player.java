package org.smartreaction.battletechdomination.model.players;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.*;
import org.smartreaction.battletechdomination.model.cards.actions.*;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Retreat;
import org.smartreaction.battletechdomination.model.cards.resource.AdvancedFactory;
import org.smartreaction.battletechdomination.model.cards.resource.BasicFactory;
import org.smartreaction.battletechdomination.model.cards.resource.MunitionsFactory;
import org.smartreaction.battletechdomination.model.cards.support.BattlefieldSalvage;
import org.smartreaction.battletechdomination.model.cards.support.HiddenBase;
import org.smartreaction.battletechdomination.model.cards.support.Refinery;
import org.smartreaction.battletechdomination.model.cards.support.attack.CloseAirSupport;
import org.smartreaction.battletechdomination.model.cards.support.attack.LongTomBattery;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ExpertMechTechs;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ForwardBase;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class Player {
    protected String playerName;

    protected List<Card> deck = new ArrayList<>();
    protected List<Card> hand = new ArrayList<>();
    protected List<Card> discard = new ArrayList<>();

    protected List<Card> resourcesPlayed = new ArrayList<>();
    protected List<Card> supportCardsPlayed = new ArrayList<>();

    protected List<Unit> deploymentZone = new ArrayList<>();

    protected List<Action> actionsQueue = new ArrayList<>();

    protected List<Card> hiddenBaseCards = new ArrayList<>(1);

    protected int actions;
    protected int attack;
    protected int defense;
    protected int industry;
    protected int losTech;

    protected int turn;

    protected TurnPhase turnPhase = TurnPhase.NONE;

    protected Player opponent;

    protected Game game;

    protected int shuffles;

    protected boolean firstPlayer;

    protected boolean ignoreLosTechCost;

    protected boolean mayPutBoughtOrGainedCardsOnTopOfDeck;

    protected boolean expertMechTechsInDeploymentZone;

    protected boolean forwardBaseInDeploymentZone;

    protected boolean yourTurn;

    protected boolean boughtInfantryPlatoonThisTurn;

    protected Action currentAction;

    public void drawHandTo(int cards) {
        if (hand.size() < cards) {
            drawCards(cards - hand.size());
        }
    }

    public List<Card> drawCards(int cards) {
        List<Card> cardsDrawn = new ArrayList<>();
        game.gameLog(playerName + " drawing " + cards + " cards");
        for (int i = 0; i < cards; i++) {
            if (deck.isEmpty()) {
                shuffleDiscardIntoDeck();
            }

            if (!deck.isEmpty()) {
                Card cardToDraw = deck.remove(0);
                cardsDrawn.add(cardToDraw);
                addCardToHand(cardToDraw);
            }
        }

        return cardsDrawn;
    }

    private void shuffleDiscardIntoDeck() {
        addGameLog("Shuffling discard into deck");
        Collections.shuffle(discard);
        for (Card card : discard) {
            card.setCardLocation(CardLocation.DECK);
        }
        deck.addAll(discard);
        discard.clear();
        shuffles++;
    }

    public void addCardToHand(Card card) {
        card.setCardLocation(CardLocation.HAND);
        hand.add(card);
    }

    public void addCardToTopOfDeck(Card card) {
        card.setCardLocation(CardLocation.DECK);
        deck.add(0, card);
        addGameLog(card.getName() + " added to top of deck");
    }

    public void addCardToDeck(Card card) {
        card.setCardLocation(CardLocation.DECK);
        deck.add(card);
    }

    public void setup() {
        Collections.shuffle(deck);
        drawCards(5);
    }

    private void cardBought(Card card) {
        getGame().gameLog(playerName + " bought " + card.getName());

        if (card instanceof InfantryPlatoon) {
            boughtInfantryPlatoonThisTurn = true;
        }

        if (card instanceof QuickToAction) {
            makeYesNoAbilityChoice(card, "QuickToAction", "Add " + card.getName() + " to top of deck?");
        } else {
            cardAcquired(card);
        }
    }

    private void cardAcquired(Card card) {
        addCardToDiscard(card);
    }

    public void discardCardFromHand(Card card) {
        hand.remove(card);
        addCardToDiscard(card);
        addGameLog(playerName + " discarded " + card.getName() + " from hand");
    }

    public void discardCardFromDeploymentZone(Card card) {
        deploymentZone.remove(card);
        addCardToDiscard(card);
        addGameLog(playerName + " discarded " + card.getName() + " from deployment zone");
    }

    public void discardTopCardOfDeck() {
        Card card = deck.remove(0);
        addGameLog(playerName + " discarded " + card.getName() + " from top of deck");
        addCardToDiscard(card);
    }

    public void discardCardFromHand() {
        discardCardsFromHand(1);
    }

    public void addCardToDiscard(Card card) {
        card.setCardLocation(CardLocation.DISCARD);
        discard.add(card);
    }

    public void shuffleCardIntoDeck(Card card) {
        deck.add(card);
        Collections.shuffle(deck);
        addGameLog("Shuffled " + card.getName() + " into deck");
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    public void discardCardsFromHand(int cards) {
        String text = "Discard " + cards + " card";
        if (cards != 1) {
            text += "s";
        }
        addAction(new DiscardCardsFromHand(cards, text));
    }

    public void makeChoice(Card card, String text, Choice... choices) {
        addAction(new ChoiceAction(card, text, choices));
    }

    public void makeAbilityChoice(Card card, String abilityName, String text, Choice... choices) {
        ChoiceAction choiceAction = new ChoiceAction(card, text, choices);
        choiceAction.setAbilityName(abilityName);
        addAction(choiceAction);
    }

    public void makeYesNoAbilityChoice(Card card, String abilityName, String text) {
        addAction(new YesNoAbilityAction(card, abilityName, text));
    }

    private void cardRemovedFromPlay(Card card) {

    }

    public void gainMunitionsFactory() {
        if (!game.getMunitionsFactories().isEmpty()) {
            MunitionsFactory munitionsFactory = game.getMunitionsFactories().remove(0);
            addGameLog(playerName + " gained " + munitionsFactory.getName());
            cardAcquired(munitionsFactory);
        }
    }

    public void gainOverrunCard(Overrun overrun) {
        if (overrun instanceof HeavyCasualties) {
            gainHeavyCasualties();
        } else if (overrun instanceof RaidedSupplies) {
            gainRaidedSupplies();
        } else if (overrun instanceof CriticalHit) {
            gainCriticalHit();
        } else if (overrun instanceof Retreat) {
            gainRetreat();
        }
    }

    public void gainHeavyCasualties() {
        if (!game.getHeavyCasualties().isEmpty()) {
            HeavyCasualties heavyCasualties = game.getHeavyCasualties().remove(0);
            addGameLog(playerName + " gained " + heavyCasualties.getName());
            cardAcquired(heavyCasualties);
        }
    }

    public void gainRaidedSupplies() {
        if (!game.getRaidedSupplies().isEmpty()) {
            RaidedSupplies raidedSupplies = game.getRaidedSupplies().remove(0);
            addGameLog(playerName + " gained " + raidedSupplies.getName());
            cardAcquired(raidedSupplies);
        }
    }

    public void gainRetreat() {
        if (!game.getRetreats().isEmpty()) {
            Retreat retreat = game.getRetreats().remove(0);
            addGameLog(playerName + " gained " + retreat.getName());
            cardAcquired(retreat);
        }
    }

    public void gainCriticalHit() {
        if (!game.getCriticalHits().isEmpty()) {
            CriticalHit criticalHit = game.getCriticalHits().remove(0);
            addGameLog(playerName + " gained " + criticalHit.getName());
            cardAcquired(criticalHit);
        }
    }

    public void gainInfantryPlatoonIntoHand() {
        if (!game.getInfantryPlatoons().isEmpty()) {
            InfantryPlatoon infantryPlatoon = game.getInfantryPlatoons().remove(0);
            addGameLog(playerName + " gained " + infantryPlatoon.getName() + " into hand");
            addCardToHand(infantryPlatoon);
        }
    }

    public Card revealTopCardOfDeck() {
        List<Card> cards = revealTopCardsOfDeck(1);
        if (cards.isEmpty()) {
            return null;
        }
        return cards.get(0);
    }

    public List<Card> revealTopCardsOfDeck(int cards) {
        List<Card> revealedCards = new ArrayList<>();

        if (deck.size() < cards) {
            shuffleDiscardIntoDeck();
        }

        if (deck.isEmpty()) {
            addGameLog("No cards to reveal");
        } else {
            for (int i = 0; i < cards; i++) {
                if (deck.size() < i+1) {
                    addGameLog("No more cards to reveal");
                } else {
                    Card card = deck.get(i);
                    addGameLog("Revealed card from top of deck: " + card.getName());
                    revealedCards.add(card);
                }
            }
        }

        return revealedCards;
    }

    public void handleStrategicBombing(List<Card> revealedCards) {
        if (revealedCards.isEmpty()) {
            return;
        }

        if (revealedCards.size() < 3) {
            for (Card card : revealedCards) {
                opponent.removeCardFromDeck(card);
                opponent.addCardToDiscard(card);
                revealedCards.remove(card);
                addGameLog("Discarded " + card.getName() + " from opponent's deck");
            }
        } else {
            addAction(new DiscardCardsForStrategicBombing(revealedCards, "These cards were revealed from the top of your opponent's deck. Choose two of them to discard. The other card will be put on top of your opponent's deck."));
        }
    }

    public void putCardsOnTopOfDeckInAnyOrder(List<Card> cards) {
        addAction(new CardsOnTopOfDeckInAnyOrder(cards, "Put these cards on top of your deck in any order."));
    }

    public void moveUnitFromDeploymentZoneToHand() {
        addAction(new UnitFromDeploymentZoneToHand("Choose a unit from your deployment zone to put into your hand"));
    }

    public void addActions(int actions) {
        this.actions += actions;
    }

    public void addAttack(int attack) {
        this.attack += attack;
    }

    public void addDefense(int defense) {
        this.defense += defense;
    }

    public void addIndustry(int industry) {
        this.industry += industry;
    }

    public void addLosTech(int losTech) {
        this.losTech += losTech;
    }

    public void scrapCardFromHandOrDiscard(CardType cardType, boolean optional) {
        String text = "Scrap a";
        if (cardType == CardType.RESOURCE) {
            text += " Resource";
        }
        text += " card from your hand or discard";

        addAction(new ScrapCardFromHandOrDiscard(cardType, optional, text));
    }

    protected void scrapCardFromDiscard(Card card) {
        addGameLog("Scrapped " + card.getName() + " from discard");
        discard.remove(card);
        playerCardScrapped(card);
    }

    protected void scrapCardFromHand(Card card) {
        addGameLog("Scrapped " + card.getName() + " from hand");
        hand.remove(card);
        playerCardScrapped(card);
    }

    private void playerCardScrapped(Card card) {
        if (card instanceof TacticalCommand) {
            addGameLog("Opponent draws 2 cards from " + card.getName() + " being scrapped");
            opponent.drawCards(2);
        }
    }

    public void acquireFreeCardInSupplyAndPutOnTopOfDeck(Integer maxIndustryCost) {
        String text = "Choose a free card from the Supply to go on top of your deck";
        if (maxIndustryCost != null) {
            text += " costing up to " + maxIndustryCost + " Industry";
        }

        addAction(new FreeCardFromSupplyToTopOfDeck(maxIndustryCost, text));
    }

    public void gainFreeResourceCardIntoHand(Integer maxIndustryCost) {
        String text = "Choose a free Resource card from the Supply to go into your hand";
        if (maxIndustryCost != null) {
            text += " costing up to " + maxIndustryCost + " Industry";
        }

        addAction(new FreeResourceCardIntoHand(maxIndustryCost, text));
    }

    public void startTurn() {
        yourTurn = true;
        turn++;
        addGameLog("** " + playerName + "'s Turn " + turn + " **");
        actions = 2;
        if (!hiddenBaseCards.isEmpty()) {
            hiddenBaseCards.stream().forEach(this::addCardToHand);
            hiddenBaseCards.clear();
            addGameLog(playerName + " added cards set aside by Hidden Base to hand");
        }
        resolveActions();
    }

    public void resolveActions() {
        if (actionsQueue.isEmpty()) {
            if (turnPhase == TurnPhase.NONE) {
                startCombatPhase();
            }
        } else {
            Action action = actionsQueue.remove(0);
            resolveAction(action);
        }
    }

    private void resolveAction(Action action) {
        if (action instanceof DamageUnit) {
            CardType unitType = ((DamageUnit) action).getCardType();
            if (deploymentZone.stream().filter(u -> u.getCardType() == unitType).count() == 0) {
                resolveActions();
                return;
            }
        } else if (action instanceof DamageUnitMaxCost) {
            List<Unit> units = deploymentZone.stream().filter(u -> u.getIndustryCost() <= ((DamageUnitMaxCost) action).getMaxCost()).collect(toList());
            if (units.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof DamageUnitMinCost) {
            List<Unit> units = deploymentZone.stream().filter(u -> u.getIndustryCost() >= ((DamageUnitMinCost) action).getMinCost()).collect(toList());
            if (units.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof ScrapOpponentUnitMaxCost) {
            List<Unit> units = opponent.getDeploymentZone().stream().filter(u -> u.getIndustryCost() <= ((ScrapOpponentUnitMaxCost) action).getMaxCost()).collect(toList());
            if (units.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof UnitFromHandToTopOfDeck) {
            if (numUnitsInHand() == 0) {
                resolveActions();
                return;
            }
        } else if (action instanceof UnitFromDeploymentZoneToHand) {
            if (deploymentZone.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof CardAction) {
            Card card = ((CardAction) action).getCard();
            if (card instanceof HiddenBase || card instanceof Refinery || card instanceof MobileFireSupport || card instanceof CloseAirSupport) {
                if (hand.isEmpty()) {
                    resolveActions();
                    return;
                }
            } else if (card instanceof HeavyFireSupport) {
                if (numUnitsInHand() == 0) {
                    resolveActions();
                    return;
                }
            } else if (card instanceof BattlefieldSalvage) {
                if (hand.isEmpty() && deploymentZone.isEmpty()) {
                    resolveActions();
                    return;
                }
            }
        } else if (action instanceof FreeCardFromSupplyToTopOfDeck) {
            if (getGame().getSupplyGrid().isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof DamageOpponentUnit) {
            if (opponent.getDeploymentZone().isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof DamageOpponentUnitMaxCost) {
            List<Unit> units = opponent.getDeploymentZone().stream().filter(u -> u.getIndustryCost() <= ((DamageOpponentUnitMaxCost) action).getMaxCost()).collect(toList());
            if (units.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof FreeResourceCardIntoHand) {
            List<Card> resourceCards = new ArrayList<>();
            if (!game.getAdvancedFactories().isEmpty()) {
                resourceCards.add(new AdvancedFactory());
            }
            if (!game.getBasicFactories().isEmpty()) {
                resourceCards.add(new BasicFactory());
            }
            if (!game.getMunitionsFactories().isEmpty()) {
                resourceCards.add(new MunitionsFactory());
            }

            for (Card card : game.getSupply()) {
                if (card instanceof Resource) {
                    resourceCards.add(card);
                }
            }

            if (resourceCards.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof ScrapCardFromHandOrDiscard) {
            if (hand.isEmpty() && discard.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof CardFromHandToTopOfDeck || action instanceof DiscardCardsFromHand) {
            if (hand.isEmpty()) {
                resolveActions();
                return;
            }
        } else if (action instanceof QuadERPPCs) {
            if (getHandSize() < 2) {
                resolveActions();
                return;
            }
        }

        currentAction = action;
    }

    @SuppressWarnings({"ConstantConditions", "SuspiciousMethodCalls"})
    public void actionResult(Action action, ActionResult result) {
        if (result.getSelectedCards().isEmpty() && result.getChoiceSelected() == null) {
            currentAction = null;
            resolveActions();
            return;
        }

        Card card = null;

        Integer choice = result.getChoiceSelected();

        if (!result.getSelectedCards().isEmpty()) {
            card = result.getSelectedCards().get(0);
        }

        if (action instanceof DamageUnit || action instanceof DamageUnitMaxCost || action instanceof DamageUnitMinCost) {
            cardDamaged(card);
        } else if (action instanceof ScrapForwardBaseOnOverrun) {
            if (choice == 1) {
                addGameLog("Scrapped Forward Base");
                forwardBaseInDeploymentZone = false;
            } else {
                gainOverrunCard((Overrun) card);
            }
        } else if (action instanceof ScrapOpponentUnitMaxCost) {
            opponent.scrapUnitFromDeploymentZone((Unit) card);
        } else if (action instanceof UnitFromHandToTopOfDeck) {
            hand.remove(card);
            addGameLog(this.getPlayerName() + " put " + card.getName() + " from hand on top of deck");
            addCardToTopOfDeck(card);
        } else if (action instanceof CardAction) {
            Card cardActionCard = ((CardAction) action).getCard();
            if (cardActionCard instanceof HiddenBase) {
                hand.remove(card);
                hiddenBaseCards.add(card);
            } else if (cardActionCard instanceof Refinery) {
                scrapCardFromHand(card);
                gainFreeResourceCardIntoHand(card.getIndustryCost() + 3);
            } else if (cardActionCard instanceof MobileFireSupport) {
                discardCardFromHand(card);
                attack++;
            } else if (cardActionCard instanceof HeavyFireSupport) {
                discardCardFromHand(card);
                addOpponentAction(new DamageUnit());
            } else if (cardActionCard instanceof CloseAirSupport) {
                discardCardFromHand(card);
                if (card instanceof Resource) {
                    addOpponentAction(new DamageUnit());
                } else if (card instanceof Unit) {
                    addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
                } else if (card instanceof Support) {
                    addOpponentAction(new DamageUnit());
                    getOpponent().gainHeavyCasualties();
                }
            } else if (cardActionCard instanceof LongTomBattery) {
                discardCardFromHand(card);
                if (card instanceof Unit) {
                    getOpponent().gainRaidedSupplies();
                } else if (card instanceof Resource || card instanceof Support) {
                    addOpponentAction(new DamageUnit());
                }
            } else if (cardActionCard instanceof QuadERPPCs) {
                List<Card> selectedCards = ((CardAction) action).getSelectedCards();
                selectedCards.stream().forEach(this::discardCardFromHand);
                opponent.gainHeavyCasualties();
            } else if (cardActionCard instanceof BattlefieldSalvage) {
                if (card.getCardLocation() == CardLocation.HAND) {
                    discardCardFromHand(card);
                } else if (card.getCardLocation() == CardLocation.DEPLOYMENT_ZONE) {
                    discardCardFromDeploymentZone(card);
                }
                addIndustry(card.getIndustryCost());
            }
        } else if (action instanceof DamageOpponentUnit || action instanceof DamageOpponentUnitMaxCost) {
            getOpponent().cardDamaged(card);
        } else if (action instanceof FreeResourceCardIntoHand) {
            if (card instanceof AdvancedFactory) {
                game.getAdvancedFactories().remove(0);
            } else if (card instanceof BasicFactory) {
                game.getBasicFactories().remove(0);
            } else if (card instanceof MunitionsFactory) {
                game.getMunitionsFactories().remove(0);
            } else {
                game.getSupplyGrid().remove(card);
                game.addCardToSupplyGrid();
            }
            addGameLog("Acquired free Resource card into hand: " + card.getName());
            addCardToHand(card);
        } else if (action instanceof FreeCardFromSupplyToTopOfDeck) {
            addGameLog("Acquired free card from supply on put it on top of deck: " + card.getName());
            addCardToTopOfDeck(card);
        } else if (action instanceof ScrapCardFromHandOrDiscard) {
            if (card.getCardLocation() == CardLocation.HAND) {
                scrapCardFromHand(card);
            } else if (card.getCardLocation() == CardLocation.DISCARD) {
                scrapCardFromDiscard(card);
            }
        } else if (action instanceof UnitFromDeploymentZoneToHand) {
            deploymentZone.remove(card);
            addCardToHand(card);
        } else if (action instanceof CardsOnTopOfDeckInAnyOrder) {
            deck.addAll(0, ((CardsOnTopOfDeckInAnyOrder) action).getCards());
        } else if (action instanceof DiscardCardsForStrategicBombing) {
            List<Card> revealedCards = ((DiscardCardsForStrategicBombing) action).getRevealedCards();

            List<Card> discardedCards = ((DiscardCardsForStrategicBombing) action).getDiscardedCards();

            for (Card discardedCard : discardedCards) {
                revealedCards.remove(discardedCard);
                opponent.removeCardFromDeck(discardedCard);
                opponent.addCardToDiscard(discardedCard);
                revealedCards.remove(discardedCard);
                addGameLog("Discarded " + discardedCard.getName() + " from opponent's deck");
            }

            Card cardToPutBack = revealedCards.get(0);
            opponent.addCardToTopOfDeck(cardToPutBack);
            addGameLog("Put " + cardToPutBack.getName() + " back on top of opponent's deck");
        } else if (action instanceof CardFromHandToTopOfDeck) {
            hand.remove(card);
            addCardToTopOfDeck(card);
        } else if (action instanceof DiscardCardsFromHand) {
            List<Card> cards = ((DiscardCardsFromHand) action).getSelectedCards();
            cards.stream().forEach(this::discardCardFromHand);
        } else if (action instanceof YesNoAbilityAction) {
            YesNoAbilityAction yesNoAbilityAction = (YesNoAbilityAction) action;
            abilityChoiceMade(yesNoAbilityAction.getCard(), yesNoAbilityAction.getAbilityName(), choice);
        } else if (action instanceof ChoiceAction) {
            ChoiceAction choiceAction = (ChoiceAction) action;
            if (choiceAction.getAbilityName() != null) {
                abilityChoiceMade(choiceAction.getCard(), choiceAction.getAbilityName(), choice);
            } else {
                choiceAction.getCard().choiceMade(choice, this);
            }
        }

        currentAction = null;
        resolveActions();
    }

    public void nextPhase() {
        if (turnPhase == TurnPhase.COMBAT_START) {
            continueCombatPhase();
            endCombatPhase();
        } else if (turnPhase == TurnPhase.ACTION) {
            addGameLog("** " + playerName + " begins Buy Phase **");
            turnPhase = TurnPhase.BUY;
        } else if (turnPhase == TurnPhase.BUY) {
            addGameLog("** " + playerName + " ends turn **");
            turnPhase = TurnPhase.CLEANUP;
            endTurn();
        }
    }

    public void startCombatPhase() {
        turnPhase = TurnPhase.COMBAT_START;

        addGameLog("** " + playerName + " begins Combat Phase **");

        attack = 0;
        defense = 0;
        opponent.setAttack(0);
        opponent.setDefense(0);
    }

    public void continueCombatPhase() {
        turnPhase = TurnPhase.COMBAT;

        applyCombatPhaseBonuses();
        opponent.applyCombatPhaseBonuses();
    }

    public void endCombatPhase() {
        sumAttackAndDefense();
        opponent.sumAttackAndDefense();

        if (attack > opponent.getDefense()) {
            int difference = attack - opponent.getDefense();

            if (opponent.isForwardBaseInDeploymentZone()) {
                String text = "Do you want to scrap your Forward Base to prevent getting a " + getOverrunCard(difference).getName() + "?";
                addOpponentAction(new ScrapForwardBaseOnOverrun(difference, text));
            } else {
                opponent.gainOverrunCard(getOverrunCard(difference));
            }

            List<Card> deploymentZoneCopy = new ArrayList<>(deploymentZone);

            for (Card card : deploymentZoneCopy) {
                if (card instanceof GreatDeath) {
                    addOpponentAction(new DamageUnit());
                }
                if (card instanceof GuerrillaFighter) {
                    drawCards(1);
                }
                if (card instanceof PoorHeatManagement) {
                    discardCardsFromHand(1);
                    //noinspection SuspiciousMethodCalls
                    deploymentZone.remove(card);
                    shuffleCardIntoDeck(card);
                }
            }
        }

        if (attack > 0) {
            addOpponentAction(new DamageUnit());
        }

        addGameLog("** " + playerName + " begins Action Phase **");

        turnPhase = TurnPhase.ACTION;
    }

    public Overrun getOverrunCard(int difference) {
        if (difference >= 4) {
            return new Retreat();
        } else if (difference == 3) {
            return new CriticalHit();
        } else if (difference == 2) {
            return new RaidedSupplies();
        } else if (difference == 1) {
            return new HeavyCasualties();
        }
        return null;
    }

    public void applyCombatPhaseBonuses() {
        for (Card card : deploymentZone) {
            if (card instanceof CityFighter) {
                ((MechUnit) card).setAbilityUsed(true);
                if (opponent.getNumInfantryUnitsInDeploymentZone() >= 2) {
                    addGameLog("Gained +1 Attack from City Fighter ability");
                    attack++;
                }
            }

            if (card instanceof ECM) {
                ((MechUnit) card).setAbilityUsed(true);
                List<Card> otherDeploymentZoneCards = new ArrayList<>(deploymentZone);
                otherDeploymentZoneCards.remove(card);
                for (Card otherDeploymentZoneCard : otherDeploymentZoneCards) {
                    addGameLog("All other Mech units gained +1 Defense from ECM ability");
                    if (otherDeploymentZoneCard instanceof MechUnit) {
                        ((MechUnit) otherDeploymentZoneCard).setBonusDefense(((MechUnit) otherDeploymentZoneCard).getBonusDefense() + 1);
                    }
                }
            }

            if (card instanceof Heroic) {
                if (getNumUnitsInDeploymentZone() < opponent.getNumUnitsInDeploymentZone()) {
                    addGameLog("Gained +1 Attack and +2 Defense from Heroic ability");
                    attack++;
                    defense += 2;
                }
            }

            if (card instanceof Inspiring) {
                List<Card> deploymentZoneCopy = new ArrayList<>(deploymentZone);
                for (Card deploymentZoneCard : deploymentZoneCopy) {
                    if (deploymentZoneCard instanceof InfantryPlatoon) {
                        ((InfantryPlatoon) deploymentZoneCard).setBonusAttack(((InfantryPlatoon) deploymentZoneCard).getBonusAttack() + 1);
                    }
                }
            }

            if (card instanceof LRMFireSupport) {
                List<Card> deploymentZoneCopy = new ArrayList<>(deploymentZone);
                for (Card deploymentZoneCard : deploymentZoneCopy) {
                    if (deploymentZoneCard instanceof MechUnit) {
                        ((MechUnit) deploymentZoneCard).setBonusAttack(((MechUnit) deploymentZoneCard).getBonusAttack() + 1);
                    }
                }
            }
        }
    }

    public void sumAttackAndDefense() {
        for (Card card : deploymentZone) {
            if (card instanceof Unit) {
                attack += ((Unit) card).getAttack();
                attack += ((Unit) card).getBonusAttack();
                defense += ((Unit) card).getDefense();
                defense += ((Unit) card).getBonusDefense();
            }
        }
    }

    public void playCardFromHand(Card card) {
        if (isBuyPhase() && card.isResource()) {
            hand.remove(card);
            card.setCardLocation(CardLocation.PLAY_AREA);
            resourcesPlayed.add(card);

            game.gameLog(this.getPlayerName() + " played " + card.getName());

            card.cardPlayed(this);
        }
        else if (isActionPhase() && actions > 0) {
            game.gameLog("using action for " + card.getName());
            actions--;

            hand.remove(card);

            if (card instanceof Support || card instanceof SupportAttack || card instanceof OverrunSupport) {
                game.gameLog(this.getPlayerName() + " played " + card.getName());
                card.setCardLocation(CardLocation.PLAY_AREA);
                supportCardsPlayed.add(card);
            } else if (card instanceof SupportReaction) {
                game.gameLog(this.getPlayerName() + " played " + card.getName());
                card.setCardLocation(CardLocation.DEPLOYMENT_ZONE_REACTION);
                if (card instanceof ExpertMechTechs) {
                    expertMechTechsInDeploymentZone = true;
                } else if (card instanceof ForwardBase) {
                    forwardBaseInDeploymentZone = true;
                }
            } else if (card.isUnit()) {
                game.gameLog(this.getPlayerName() + " deployed " + card.getName());
                deployUnit((Unit) card);
            } else {
                game.gameLog("card type didn't match action type: " + card.getCardType().toString());
            }

            card.cardPlayed(this);
        }
    }

    public void deployUnit(Unit unit) {
        if (!unit.isDeployable(this)) {
            addGameLog(unit.getName() + " is not deployable");
            return;
        }

        if (unit instanceof MechUnit || unit instanceof VehicleUnit) {
            for (Card card : opponent.getDeploymentZone()) {
                if (card instanceof ActiveProbe) {
                    opponent.drawCards(1);
                }
            }
        }

        unit.setCardLocation(CardLocation.DEPLOYMENT_ZONE);
        deploymentZone.add(unit);
    }

    public void useUnitAbility(Unit unit) {
        if (unit.isAbilityAvailable(this)) {
            unit.setAbilityUsed(true);
            if (unit instanceof AC20) {
                Card card = revealTopCardOfDeck();
                if (card instanceof Resource) {
                    addOpponentAction(new DamageUnit(CardType.UNIT_MECH, "Damage a Mech Unit"));
                } else if (card instanceof Support) {
                    cardDamaged(card);
                }
            }

            if (unit instanceof Expendable) {
                cardDamaged(unit);
                attack++;
            }

            if (unit instanceof DeathFromAbove) {
                cardDamaged(unit);
                addAction(new ScrapOpponentUnitMaxCost(5, "Damage an opponent's unit costing 5 Industry or less"));
            }

            if (unit instanceof HeavyFireSupport) {
                addAction(new CardAction(unit, "Discard a Unit card from your hand to make your opponent damage a unit."));
            }

            if (unit instanceof MobileFireSupport) {
                addAction(new CardAction(unit, "Discard a card from your hand for +1 Attack."));
            }

            if (unit instanceof Overheat) {
                cardDamaged(unit);
                attack += 4;
            }

            if (unit instanceof QuadERPPCs) {
                addAction(new CardAction(unit, "Discard two cards from your hand to make your opponent gain a Heavy Casualties card."));
            }

            if (unit instanceof Scout) {
                Card card = opponent.revealTopCardOfDeck();

                Choice choice1 = new Choice(1, "Opponent discards " + card.getName());
                Choice choice2 = new Choice(2, "Opponent puts " + card.getName() + " back on top of deck");

                this.makeAbilityChoice(unit, "Scout", "Opponent's top card of deck is " + card.getName() + ". Do you want to discard it or put it back?", choice1, choice2);
            }
        }
    }

    public void endTurn() {
        actions = 0;
        attack = 0;
        defense = 0;
        industry = 0;
        losTech = 0;

        ignoreLosTechCost = false;
        mayPutBoughtOrGainedCardsOnTopOfDeck = false;
        boughtInfantryPlatoonThisTurn = false;

        for (Card card : resourcesPlayed) {
            addCardToDiscard(card);
            cardRemovedFromPlay(card);
        }

        for (Card card : supportCardsPlayed) {
            addCardToDiscard(card);
            cardRemovedFromPlay(card);
        }

        for (Card card : deploymentZone) {
            if (card instanceof MechUnit) {
                ((MechUnit) card).setAbilityUsed(false);
            }
        }

        resourcesPlayed.clear();
        supportCardsPlayed.clear();

        for (Card card : hand) {
            addCardToDiscard(card);
        }
        hand.clear();

        drawCards(5);

        yourTurn = false;

        turnPhase = TurnPhase.NONE;

        for (Card card : deploymentZone) {
            if (card instanceof TacticalCommand) {
                drawCards(2);
                if (hand.size() > 5) {
                    discardCardsFromHand(hand.size() - 5);
                    //todo don't end turn until card have been discarded
                }
            }
        }

        game.turnEnded();
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();

        cards.addAll(hand);
        cards.addAll(deck);
        cards.addAll(discard);
        cards.addAll(resourcesPlayed);
        cards.addAll(supportCardsPlayed);
        cards.addAll(deploymentZone);

        return cards;
    }

    public void scrapUnitFromDeploymentZone(Unit unit) {
        addGameLog("Scrapped " + unit.getName());
        deploymentZone.remove(unit);
        playerCardScrapped(unit);
    }

    public void cardDamaged(Card card) {
        if (card instanceof TotemMech && getNumMechUnitsInDeploymentZone() == 1) {
            addGameLog(card.getName() + " was not damaged due to Totem Mech ability");
            return;
        }

        addGameLog("Damaged " + card.getName());
        deploymentZone.remove(card);

        if (card instanceof TacticalCommand) {
            addGameLog("Opponent draws 2 cards from damaging " + card.getName());
            opponent.drawCards(2);
        }

        if (card instanceof CounterAttack) {
            Unit unit = opponent.getUnitWithHighestIndustryCost(opponent.getDeploymentZone());
            if (unit != null) {
                opponent.cardDamaged(unit);
            }
        }

        if (card instanceof Durable) {
            drawCards(1);
        }

        if (card instanceof MechUnit) {
            for (Card opponentCard : opponent.getDeploymentZone()) {
                if (opponentCard instanceof TargetingComputer) {
                    opponent.drawCards(1);
                }
            }
        }

        if (card instanceof HeavyArmor) {
            makeYesNoAbilityChoice(card, "HeavyArmor", "Shuffle " + card.getName() + " into deck?");
        } else if (card instanceof Tank) {
            addGameLog(card.getName() + " was scrapped due to Tank ability");
            playerCardScrapped(card);
        } else {
            if (card instanceof MechUnit && expertMechTechsInDeploymentZone) {
                makeYesNoAbilityChoice(card, "ExpertMechTechs", "Scrap Expert Mech Techs to put " + card.getName() + " into your hand instead of your discard pile?");
            } else {
                addCardToDiscard(card);
                cardRemovedFromPlay(card);
            }
        }
    }

    public void abilityChoiceMade(Card card, String abilityName, int choice) {
        switch (abilityName) {
            case "ExpertMechTechs":
                if (choice == 1) {
                    expertMechTechsInDeploymentZone = false;
                    addGameLog("Scrapped Expert Mech Techs");
                    addCardToHand(card);
                } else {
                    addCardToDiscard(card);
                    cardRemovedFromPlay(card);
                }
                break;
            case "HeavyArmor":
                if (choice == 1) {
                    shuffleCardIntoDeck(card);
                } else {
                    addCardToDiscard(card);
                    cardRemovedFromPlay(card);
                }
                break;
            case "QuickToAction":
                if (choice == 1) {
                    addCardToTopOfDeck(card);
                } else {
                    addCardToDiscard(card);
                }
                break;
            case "ReconInForce":
                if (choice == 1) {
                    discardCardsFromHand(1);
                }
                break;
            case "Scout":
                if (choice == 1) {
                    addGameLog("Chose to discard top card of opponent's deck");
                    opponent.discardTopCardOfDeck();
                } else {
                    addGameLog("Chose to keep top card of opponent's deck on top of deck");
                }
                break;
            case "ScrapForwardBaseOnOverrun":
                if (choice == 1) {
                    addGameLog("Scrapped Forward Base");
                    forwardBaseInDeploymentZone = false;
                } else {
                    gainOverrunCard((Overrun) card);
                }
                break;
            case "Versatile":
                if (choice == 1) {
                    addGameLog("Chose +1 Card");
                    drawCards(1);
                } else if (choice == 2) {
                    addGameLog("Chose +1 Action");
                    addActions(1);
                } else if (choice == 3) {
                    addGameLog("Chose +1 Industry");
                    addIndustry(1);
                }
                break;
        }
    }

    public void addOpponentAction(Action action) {
        opponent.getActionsQueue().add(action);
    }

    public void addGameLog(String log) {
        getGame().gameLog(log);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getShuffles() {
        return shuffles;
    }

    public void setShuffles(int shuffles) {
        this.shuffles = shuffles;
    }

    public int getActions() {
        return actions;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getIndustry() {
        return industry;
    }

    public int getLosTech() {
        return losTech;
    }

    public int getTurn() {
        return turn;
    }

    public List<Action> getActionsQueue() {
        return actionsQueue;
    }

    public List<Unit> getDeploymentZone() {
        return deploymentZone;
    }

    public int getNumUnitsInDeploymentZone() {
        return deploymentZone.size();
    }

    public int getNumMechUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof MechUnit).count();
    }

    public int getNumInfantryUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof InfantryUnit).count();
    }

    public void ignoreLosTechCost() {
        this.ignoreLosTechCost = true;
    }

    public void mayPutBoughtOrGainedCardsOnTopOfDeck() {
        mayPutBoughtOrGainedCardsOnTopOfDeck = true;
    }

    public TurnPhase getTurnPhase() {
        return turnPhase;
    }

    public boolean isExpertMechTechsInDeploymentZone() {
        return expertMechTechsInDeploymentZone;
    }

    public boolean isForwardBaseInDeploymentZone() {
        return forwardBaseInDeploymentZone;
    }

    public Unit getUnitWithHighestIndustryCost(List<Unit> units) {
        if (units == null || units.isEmpty()) {
            return null;
        }
        List<Unit> sortedCards = units.stream().sorted((u1, u2) -> Integer.compare(u2.getIndustryCost(), u1.getIndustryCost())).collect(toList());
        return sortedCards.get(0);
    }

    public List<Card> getCardsInPlayArea() {
        if (turnPhase == TurnPhase.ACTION) {
            return supportCardsPlayed;
        } else if (turnPhase == TurnPhase.BUY) {
            return resourcesPlayed;
        }
        return new ArrayList<>();
    }

    public boolean isCardActionable(Card card) {
        if (!yourTurn) {
            return false;
        }
        if (card.getCardLocation() == CardLocation.HAND) {
            if (card.isResource() && isBuyPhase()) {
                return true;
            } else if (isActionPhase() && actions > 0) {
                if (card.isUnit()) {
                    return true;
                } else if (card.isSupport()) {
                    return true;
                }
            }
        } else if (card.getCardLocation() == CardLocation.DEPLOYMENT_ZONE) {
            return ((Unit) card).isAbilityAvailable(this);
        }
        return false;
    }

    public boolean isCardBuyable(Card card) {
        //noinspection SimplifiableIfStatement
        if (boughtInfantryPlatoonThisTurn && card instanceof InfantryPlatoon) {
            return false;
        }
        return yourTurn && isBuyPhase() && card.getIndustryCost() <= industry && card.getLosTechCost() <= losTech;
    }

    public int getHandSize() {
        return hand.size();
    }

    public int numUnitsInHand() {
        return (int) hand.stream().filter(Card::isUnit).count();
    }

    @SuppressWarnings("unused")
    public int getDeckSize() {
        return deck.size();
    }

    @SuppressWarnings("unused")
    public int getDiscardSize() {
        return discard.size();
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public boolean isCombatStart() {
        return turnPhase == TurnPhase.COMBAT_START;
    }

    public boolean isActionPhase() {
        return turnPhase == TurnPhase.ACTION;
    }

    public boolean isBuyPhase() {
        return turnPhase == TurnPhase.BUY;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void buyCard(Card card) {
        if (isCardBuyable(card)) {
            industry -= card.getIndustryCost();
            losTech -= card.getLosTechCost();

            if (!(card instanceof BaseSupply)) {
                getGame().getSupplyGrid().remove(card);
                getGame().addCardToSupplyGrid();
            }

            cardBought(card);
        }
    }

    public int getPoints() {
        int points = 0;

        for (Card card : getAllCards()) {
            if (card.getLosTechCost() > 0) {
                points += 2;
            }
            if (card instanceof Overrun) {
                points -= ((Overrun) card).getOverrunAmount();
            }
        }

        return points;
    }

    public int getCurrentDefense() {
        int defense = 0;

        for (Unit unit : getDeploymentZone()) {
            defense += unit.getDefense();
        }

        return defense;
    }

    public int getCurrentAttack() {
        int attack = 0;

        for (Unit unit : getDeploymentZone()) {
            attack += unit.getAttack();
        }

        return attack;
    }

    public void playAll() {
        List<Card> copyOfHand = new ArrayList<>(hand);
        copyOfHand.stream().filter(card -> card instanceof Resource).forEach(this::playCardFromHand);
    }

    public void addAction(Action action) {
        actionsQueue.add(action);
        if (yourTurn && currentAction == null) {
            resolveActions();
        }
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public boolean isResourceCardInHand() {
        return hand.stream().filter(Card::isResource).count() > 0;
    }
}
