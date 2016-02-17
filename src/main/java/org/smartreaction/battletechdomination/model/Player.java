package org.smartreaction.battletechdomination.model;

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
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    private String playerName;

    private List<Card> deck = new ArrayList<>();
    private List<Card> hand = new ArrayList<>();
    private List<Card> discard = new ArrayList<>();

    private List<Card> resourcesPlayed = new ArrayList<>();
    private List<Card> supportCardsPlayed = new ArrayList<>();

    private List<Card> deploymentZone = new ArrayList<>();

    private List<Action> actionsQueue = new ArrayList<>();

    private List<Card> setAside = new ArrayList<>();

    private int actions;
    private int attack;
    private int defense;
    private int industry;
    private int losTech;

    private int turns;

    private TurnPhase turnPhase;

    private Player opponent;

    private Game game;

    private int shuffles;

    private boolean firstPlayer;

    private boolean ignoreLosTechCost;

    private boolean mayPutBoughtOrGainedCardsOnTopOfDeck;

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
                addGameLog("Added " + cardToDraw.getName() + " to hand");
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

    private void cardBought(Card card) {
        if (card instanceof QuickToAction) {
            makeYesNoChoice(card, "QuickToAction", "Add " + card.getName() + " to top of deck?");
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
        addGameLog("Discarded " + card.getName() + " from hand");
    }

    public void discardCardFromDeploymentZone(Card card) {
        deploymentZone.remove(card);
        addCardToDiscard(card);
        addGameLog("Discarded " + card.getName() + " from deployment zone");
    }

    public void opponentDiscardsCard() {
        addGameLog("Opponent discarding card");
        opponent.discardCardFromHand();
    }

    public void discardTopCardOfDeck() {
        Card card = deck.remove(0);
        addGameLog("Discarded " + card.getName() + " from top of deck");
        addCardToDiscard(card);
    }

    public void discardCardFromHand() {
        discardCardsFromHand(1, false);
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

    public List<Card> discardCardsFromHandOrDeploymentZone(int cards, boolean optional) {
        List<Card> cardsToDiscard = getCardsToDiscardFromHandOrDeploymentZone(cards, optional);

        for (Card card : cardsToDiscard) {
            if (card.getCardLocation() == CardLocation.HAND) {
                discardCardFromHand(card);
            } else if (card.getCardLocation() == CardLocation.DEPLOYMENT_ZONE) {
                discardCardFromDeploymentZone(card);
            }
        }

        return cardsToDiscard;
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    public List<Card> discardCardsFromHand(int cards, boolean optional) {
        List<Card> cardsToDiscard = getCardsToDiscardFromHand(cards, optional);

        for (Card card : cardsToDiscard) {
            discardCardFromHand(card);
        }

        return cardsToDiscard;
    }

    public void discardUnitFromHandToDamageOpponentUnit() {
        Unit unit = getUnitFromHandToDiscardToDamageOpponentUnit();
        if (unit != null) {
            discardCardFromHand(unit);
            addOpponentAction(new DamageUnit());
        }
    }

    public void discardCardFromHandForPlusOneAttack() {
        Card card = getCardFromHandToDiscardForPlusOneAttack();
        if (card != null) {
            attack++;
        }
    }

    public abstract Card getCardFromHandToDiscardForPlusOneAttack();

    public abstract Unit getUnitFromHandToDiscardToDamageOpponentUnit();

    public abstract List<Card> getCardsToDiscardFromHand(int cards, boolean optional);

    public abstract List<Card> getCardsToDiscardFromHandOrDeploymentZone(int cards, boolean optional);

    public abstract List<Card> getCardsFromHandToAddToTopOfDeck(int cards);

    public abstract void makeChoice(Card card, Choice... choices);

    public abstract void makeYesNoChoice(Card card, String choiceIdentifier, String text);

    public void addCardsFromHandToTopOfDeck(int cards) {
        List<Card> cardsFromHand = getCardsFromHandToAddToTopOfDeck(cards);
        for (Card card : cardsFromHand) {
            hand.remove(card);
            addCardToTopOfDeck(card);
        }

    }

    private void cardRemovedFromPlay(Card card) {

    }

    public void gainMunitionsFactory() {
        if (!game.getMunitionsFactories().isEmpty()) {
            MunitionsFactory munitionsFactory = game.getMunitionsFactories().remove(0);
            addGameLog("Gained " + munitionsFactory.getName());
            cardAcquired(munitionsFactory);
        }
    }

    public void gainHeavyCasualties() {
        if (!game.getHeavyCasualties().isEmpty()) {
            HeavyCasualties heavyCasualties = game.getHeavyCasualties().remove(0);
            addGameLog("Gained " + heavyCasualties.getName());
            cardAcquired(heavyCasualties);
        }
    }

    public void gainRaidedSupplies() {
        if (!game.getRaidedSupplies().isEmpty()) {
            RaidedSupplies raidedSupplies = game.getRaidedSupplies().remove(0);
            addGameLog("Gained " + raidedSupplies.getName());
            cardAcquired(raidedSupplies);
        }
    }

    public void gainRetreat() {
        if (!game.getRetreats().isEmpty()) {
            Retreat retreat = game.getRetreats().remove(0);
            addGameLog("Gained " + retreat.getName());
            cardAcquired(retreat);
        }
    }

    public void gainCriticalHit() {
        if (!game.getCriticalHits().isEmpty()) {
            CriticalHit criticalHit = game.getCriticalHits().remove(0);
            addGameLog("Gained " + criticalHit.getName());
            cardAcquired(criticalHit);
        }
    }

    public void gainInfantryPlatoonIntoHand() {
        if (!game.getInfantryPlatoons().isEmpty()) {
            InfantryPlatoon infantryPlatoon = game.getInfantryPlatoons().remove(0);
            addGameLog("Gained " + infantryPlatoon.getName() + " into hand");
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
        List<Card> cardsToDiscard;
        if (revealedCards.size() < 3) {
            cardsToDiscard = new ArrayList<>(revealedCards);
        } else {
            cardsToDiscard = chooseCardsToDiscardForStrategicBombing(revealedCards);
        }
        for (Card card : cardsToDiscard) {
            opponent.removeCardFromDeck(card);
            opponent.addCardToDiscard(card);
            revealedCards.remove(card);
            addGameLog("Discarded " + card.getName() + " from opponent's deck");
        }
        if (!revealedCards.isEmpty()) {
            Card card = revealedCards.get(0);
            opponent.addCardToTopOfDeck(card);
            addGameLog("Put " + card.getName() + " back on top of opponent's deck");
        }
    }

    public abstract List<Card> chooseCardsToDiscardForStrategicBombing(List<Card> cards);

    public void putCardsOnTopOfDeckInAnyOrder(List<Card> cards) {
        List<Card> orderedCards = chooseOrderToPutCardsOnTopOfDeck(cards);
        deck.addAll(0, orderedCards);
    }

    public abstract List<Card> chooseOrderToPutCardsOnTopOfDeck(List<Card> cards);

    public void addUnitFromDeploymentZoneToHand() {
        Card card = chooseCardFromDeploymentZoneToAddToHand();
        if (card != null) {
            deploymentZone.remove(card);
            hand.add(card);
            addGameLog("Moved " + card.getName() + " from deployment zone into hand");
        }
    }

    public abstract Card chooseCardFromDeploymentZoneToAddToHand();

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

    public void scrapCardFromHandOrDiscard(CardType cardType) {
        scrapCardsFromHandOrDiscard(1, cardType);
    }

    public Card scrapCardFromHand(CardType cardType, boolean optional) {
        Card card = null;
        if (!hand.isEmpty()) {
            card = getCardToScrapFromHand(cardType, optional);
            if (card != null) {
                scrapCardFromHand(card);
            }
        }
        return card;
    }

    public int scrapCardsFromHandOrDiscard(int cards, CardType cardType) {
        List<List<Card>> cardsToScrap = getCardsToOptionallyScrapFromDiscardOrHand(cards, cardType);

        List<Card> cardsToScrapFromDiscard = cardsToScrap.get(0);
        List<Card> cardsToScrapFromHand = cardsToScrap.get(1);

        for (Card card : cardsToScrapFromDiscard) {
            scrapCardFromDiscard(card);
        }

        for (Card card : cardsToScrapFromHand) {
            scrapCardFromHand(card);
        }

        return cardsToScrap.size();
    }

    public abstract List<List<Card>> getCardsToOptionallyScrapFromDiscardOrHand(int cards, CardType cardType);

    public abstract Card getCardToScrapFromHand(CardType cardType, boolean optional);

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
    }

    public void acquireFreeCardInSupplyAndPutOnTopOfDeck(Integer maxIndustryCost) {
        if (!getGame().getSupply().isEmpty()) {
            Card card = chooseFreeCardFromSupplyToPutOnTopOfDeck(maxIndustryCost);
            if (card != null) {
                addGameLog("Acquired free card from supply on put it on top of deck: " + card.getName());
                addCardToTopOfDeck(card);
            }
        }
    }
    
    public abstract Card chooseFreeCardFromSupplyToPutOnTopOfDeck(Integer maxIndustryCost);

    public void gainFreeResourceCardIntoHand(int maxIndustryCost) {
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

        if (!resourceCards.isEmpty()) {
            Card card = chooseFreeResourceCardToPutIntoHand(maxIndustryCost);
            if (card != null) {
                addGameLog("Acquired free Resource card into hand: " + card.getName());
                addCardToHand(card);
                if (card instanceof AdvancedFactory) {
                    game.getAdvancedFactories().remove(0);
                } else if (card instanceof BasicFactory) {
                    game.getBasicFactories().remove(0);
                } else if (card instanceof MunitionsFactory) {
                    game.getMunitionsFactories().remove(0);
                } else {
                    game.getSupply().remove(card);
                    game.addCardToSupplyGrid();
                }
            }
        }
    }

    public abstract Card chooseFreeResourceCardToPutIntoHand(int maxIndustryCost);

    public void setupTurn() {
        actions = 2;
        hand.addAll(setAside);
        setAside.clear();
        startCombatPhase();
    }

    public void startCombatPhase() {
        turnPhase = TurnPhase.COMBAT_START;

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

            if (difference >= 4) {
                opponent.gainRetreat();
            } else if (difference == 3) {
                opponent.gainCriticalHit();
            } else if (difference == 2) {
                opponent.gainRaidedSupplies();
            } else if (difference == 1) {
                opponent.gainHeavyCasualties();
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
                    discardCardsFromHand(1, false);
                    deploymentZone.remove(card);
                    shuffleCardIntoDeck(card);
                }
            }
        }

        if (attack > 0) {
            addOpponentAction(new DamageUnit());
        }
    }

    public void applyCombatPhaseBonuses() {
        for (Card card : deploymentZone) {
            if (card instanceof CityFighter) {
                ((MechUnit) card).setAbilityUsed(true);
                if (opponent.getNumInfantryUnitsInDeploymentZone() >= 2) {
                    addGameLog("Gained +1 Attack from CityFighter ability");
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

    public abstract void takeTurn();

    public void playCard(Card card) {
        if (actions > 0) {
            actions--;

            game.gameLog("Played card: " + card.getName());

            hand.remove(card);

            if (card instanceof Resource) {
                resourcesPlayed.add(card);
            } else if (card instanceof Support || card instanceof SupportAttack) {
                supportCardsPlayed.add(card);
            } else if (card instanceof SupportReaction) {
                deploymentZone.add(card);
            } else if (card instanceof Unit) {
                deployUnit((Unit) card);
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
    }

    public void useMechAbility(MechUnit unit) {
        if (unit.isAbilityAvailable(this)) {
            unit.setAbilityUsed(true);
            if (unit instanceof AC20) {
                Card card = revealTopCardOfDeck();
                if (card instanceof Resource) {
                    addOpponentAction(new DamageUnit(CardType.UNIT_MECH));
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
                addOpponentAction(new ScrapUnitMaxCost(5));
            }

            if (unit instanceof HeavyFireSupport) {
                discardUnitFromHandToDamageOpponentUnit();
            }

            if (unit instanceof MobileFireSupport) {
                discardCardFromHandForPlusOneAttack();
            }

            if (unit instanceof Overheat) {
                cardDamaged(unit);
                attack += 4;
            }

            if (unit instanceof QuadERPPCs) {
                List<Card> cards = discardCardsFromHand(2, false);
                if (cards.size() == 2) {
                    opponent.gainHeavyCasualties();
                }
            }
        }
    }

    public void endTurn() {
        addGameLog("Ending turn");

        turns++;

        turnPhase = TurnPhase.NONE;

        actions = 0;
        attack = 0;
        defense = 0;
        industry = 0;
        losTech = 0;

        ignoreLosTechCost = false;
        mayPutBoughtOrGainedCardsOnTopOfDeck = false;

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

    public void damageMech() {
        damageUnit(CardType.UNIT_MECH);
    }

    public void damageInfantry() {
        damageUnit(CardType.UNIT_INFANTRY);
    }

    public void damageUnit(CardType type) {
        Unit unit = chooseUnitToDamage(type);
        if (unit != null) {
            cardDamaged(unit);
        }
    }

    public abstract MechUnit chooseUnitToDamage(CardType type);

    public void cardDamaged(Card card) {
        addGameLog("Damaged " + card.getName());
        deploymentZone.remove(card);

        if (card instanceof CounterAttack) {
            addOpponentAction(new DamageUnitWithHighestIndustryCost());
        }

        if (card instanceof Durable) {
            drawCards(1);
        }

        if (card instanceof HeavyArmor) {
            makeYesNoChoice(card, "HeavyArmor", "Shuffle " + card.getName() + " into deck?");
        } else {
            addCardToDiscard(card);
            cardRemovedFromPlay(card);
        }
    }

    public void yesNoChoiceMade(Card card, String choiceIdentifier, int choice) {
        if (choiceIdentifier.equals("HeavyArmor")) {
            if (choice == 1) {
                shuffleCardIntoDeck(card);
            } else {
                addCardToDiscard(card);
                cardRemovedFromPlay(card);
            }
        } else if (choiceIdentifier.equals("QuickToAction")) {
            if (choice == 1) {
                addCardToTopOfDeck(card);
            } else {
                addCardToDiscard(card);
            }
        }
    }
    
    public void versatileChoiceMade(int choice) {
        if (choice == 1) {
            addGameLog("Chose +1 Card1");
            drawCards(1);
        } else if (choice == 2) {
            addGameLog("Chose +1 Action1");
            addActions(1);
        } else if (choice == 3) {
            addGameLog("Chose +1 Industry");
            addIndustry(1);
        }
    }

    public void damageOpponentUnit() {
        Unit unit = chooseOpponentUnitToDamage();
        if (unit != null) {
            getOpponent().cardDamaged(unit);
        }
    }

    public abstract Unit chooseOpponentUnitToDamage();

    public void addOpponentAction(Action action) {
        opponent.getActionsQueue().add(action);
    }

    public void setAsideCardFromHand() {
        Card card = chooseCardToSetAside();
        if (card != null) {
            setAside.add(card);
        }
    }

    public abstract Card chooseCardToSetAside();

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

    public int getTurns() {
        return turns;
    }

    public List<Action> getActionsQueue() {
        return actionsQueue;
    }

    public List<Card> getDeploymentZone() {
        return deploymentZone;
    }

    public int getNumUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof Unit).count();
    }

    public int getNumMechUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof MechUnit).count();
    }

    public int getNumInfantryUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof InfantryUnit).count();
    }

    public List<Card> getSetAside() {
        return setAside;
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

    public void setTurnPhase(TurnPhase turnPhase) {
        this.turnPhase = turnPhase;
    }
}
