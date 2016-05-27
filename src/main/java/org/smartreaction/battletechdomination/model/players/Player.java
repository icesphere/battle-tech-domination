package org.smartreaction.battletechdomination.model.players;

import org.smartreaction.battletechdomination.model.Choice;
import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.TurnPhase;
import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.abilities.SupportActionChoice;
import org.smartreaction.battletechdomination.model.cards.abilities.unit.*;
import org.smartreaction.battletechdomination.model.cards.actions.*;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Retreat;
import org.smartreaction.battletechdomination.model.cards.resource.MunitionsFactory;
import org.smartreaction.battletechdomination.model.cards.resource.WarBonds;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ExpertMechTechs;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ForwardBase;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.InfantryPlatoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public abstract class Player {
    protected String playerName;

    protected List<Card> deck = new ArrayList<>();
    protected List<Card> hand = new ArrayList<>();
    protected List<Card> discard = new ArrayList<>();

    protected List<Card> cardsPlayed = new ArrayList<>();

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
        addGameLog(playerName + " shuffling discard into deck");
        Collections.shuffle(discard);
        deck.addAll(discard);
        discard.clear();
        shuffles++;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void addCardToTopOfDeck(Card card) {
        deck.add(0, card);
        addGameLog(playerName + " added " + card.getName() + " to top of deck");
    }

    public void addCardToDeck(Card card) {
        deck.add(card);
    }

    public void addCardToPlayArea(Card card) {
        cardsPlayed.add(card);
    }

    public void setup() {
        Collections.shuffle(deck);
        if (isFirstPlayer()) {
            drawCards(3);
        } else {
            drawCards(5);
        }
    }

    private void cardBought(Card card) {
        getGame().gameLog(playerName + " bought " + card.getName());

        if (card instanceof InfantryPlatoon) {
            boughtInfantryPlatoonThisTurn = true;
        }

        if (card.isUnit() && ((Unit) card).hasBuyAbility()) {
            ((Unit) card).applyBuyAbility(this);
        } else {
            cardAcquired(card);
            card.cardBought(this);
        }
    }

    public void cardAcquired(Card card) {
        if (card.isOverrun()) {
            Optional<Unit> spotterUnit = opponent.getDeploymentZone().stream().filter(u -> u instanceof Spotter).findAny();
            if (spotterUnit.isPresent()) {
                addGameLog(playerName + " had to put " + card.getName() + " on top of deck due to Spotter ability on " + spotterUnit.get().getName());
                addCardToTopOfDeck(card);
            } else {
                addCardToDiscard(card);
            }
        } else if (mayPutBoughtOrGainedCardsOnTopOfDeck) {
            addCardToTopOfDeck(card);
        } else {
            addCardToDiscard(card);
        }
    }

    public void discardCardFromHand(Card card) {
        hand.remove(card);
        addCardToDiscard(card);
        addGameLog(playerName + " discarded " + card.getName() + " from hand");
    }

    public void discardCardFromDeploymentZone(Unit unit) {
        deploymentZone.remove(unit);
        addCardToDiscard(unit);
        addGameLog(playerName + " discarded " + unit.getName() + " from deployment zone");
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
        discard.add(card);
    }

    public void shuffleCardIntoDeck(Card card) {
        deck.add(card);
        Collections.shuffle(deck);
        addGameLog(playerName + " shuffled " + card.getName() + " into deck");
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

    public void makeSupportActionChoice(SupportActionChoice card, String text, Choice... choices) {
        addAction(new SupportChoiceAction(card, text, choices));
    }

    public void makeYesNoSupportActionChoice(SupportActionChoice card, String text) {
        addAction(new YesNoAbilityAction(card, text));
    }

    public void makeUnitAbilityChoice(UnitChoiceAbility ability, String text, Choice... choices) {
        addAction(new UnitAbilityChoiceAction(ability, text, choices));
    }

    public void makeYesNoUnitAbilityChoice(UnitChoiceAbility ability, String text) {
        addAction(new YesNoUnitAbilityChoiceAction(ability, text));
    }

    @SuppressWarnings("UnusedParameters")
    public void cardRemovedFromPlay(Card card) {
        //todo may need in the future
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

    public void revealHand() {
        addGameLog(playerName + " revealed their hand: " + getGame().getCardsAsString(hand));
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
            addGameLog(playerName + " had no cards to reveal");
        } else {
            for (int i = 0; i < cards; i++) {
                if (deck.size() < i+1) {
                    addGameLog("No more cards to reveal");
                } else {
                    Card card = deck.get(i);
                    addGameLog(playerName + " revealed " + card.getName() + " from top of deck");
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
                addGameLog(playerName + " discarded " + card.getName() + " from " + getOpponent().getPlayerName() + "'s deck");
            }
        } else {
            addAction(new DiscardCardsForStrategicBombing(revealedCards, "Revealed cards from the top of your opponent's deck: " + getGame().getCardsAsString(revealedCards) + ". Choose two of them to discard. The other card will be put on top of your opponent's deck."));
        }
    }

    public void moveUnitFromDeploymentZoneToHand() {
        addAction(new UnitFromDeploymentZoneToHand("Choose a unit from your deployment zone to put into your hand"));
    }

    public void addActions(int actions) {
        this.actions += actions;
    }

    public void addIndustry(int industry) {
        this.industry += industry;
    }

    public void addLosTech(int losTech) {
        this.losTech += losTech;
    }

    public void addAttack(int attack) {
        this.attack += attack;
    }

    public void addDefense(int defense) {
        this.defense += defense;
    }

    public void scrapCardFromHand(Card card) {
        addGameLog(playerName + " scrapped " + card.getName() + " from hand");
        hand.remove(card);
        playerCardScrapped(card);
    }

    private void playerCardScrapped(Card card) {
        if (card instanceof TacticalCommand) {
            addGameLog(opponent.getPlayerName() + " draws 2 cards due to " + card.getName() + " being scrapped");
            opponent.drawCards(2);
        }
    }

    public void acquireFreeCardInSupply() {
        addAction(new FreeCardFromSupply("Choose a free card to gain from the Supply"));
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
            } else if (turnPhase == TurnPhase.CLEANUP) {
                game.turnEnded();
            }
        } else {
            Action action = actionsQueue.remove(0);
            processNextAction(action);
        }
    }

    private void processNextAction(Action action) {
        if (action.processAction(this)) {
            currentAction = action;
        } else {
            resolveActions();
        }
    }

    public void actionResult(Action action, ActionResult result) {
        if (!result.getSelectedCards().isEmpty() || result.getChoiceSelected() != null) {
            action.processActionResult(this, result);
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

    public void clearCombatBonuses() {
        for (Unit unit : deploymentZone) {
            unit.setBonusAttack(0);
            unit.setBonusDefense(0);
        }
    }

    public void continueCombatPhase() {
        turnPhase = TurnPhase.COMBAT;

        applyCombatPhaseBonuses();
        opponent.applyCombatPhaseBonuses();
    }

    public void endCombatPhase() {
        sumAttackAndDefense();
        opponent.sumAttackAndDefense();

        getGame().gameLog(playerName + " attack = " + attack);
        getGame().gameLog(getOpponent().getPlayerName() + " defense = " + getOpponent().getDefense());

        if (attack > opponent.getDefense()) {
            int difference = attack - opponent.getDefense();

            getGame().gameLog("attack - defense = " + difference);

            Overrun overrunCard = getOverrunCard(difference);

            if (opponent.isForwardBaseInDeploymentZone()) {
                addGameLog(opponent.getPlayerName() + " scrapped Forward Base to prevent gaining " + overrunCard.getName());
                opponent.setForwardBaseInDeploymentZone(false);
            } else {
                opponent.gainOverrunCard(overrunCard);
            }

            List<Unit> deploymentZoneCopy = new ArrayList<>(deploymentZone);

            for (Unit unit : deploymentZoneCopy) {
                unit.applyOverrunOpponentAbilities(this);
            }

            List<Unit> opponentDeploymentZoneCopy = new ArrayList<>(opponent.getDeploymentZone());

            for (Unit unit : opponentDeploymentZoneCopy) {
                unit.applyOverrunByOpponentAbilities(opponent);
            }
        }

        if (attack > 0 && getOpponent().getNumUnitsInDeploymentZone() > 0) {
            addGameLog("Attack was greater than 0, " + getOpponent().getPlayerName() + " must damage a unit");
            addOpponentAction(new DamageUnit());
        }

        clearCombatBonuses();
        opponent.clearCombatBonuses();

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
        for (Unit unit : deploymentZone) {
            unit.applyCombatPhaseBonuses(this);
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
            addCardToPlayArea(card);

            game.gameLog(playerName + " played " + card.getName());

            card.cardPlayed(this);
        }
        else if (isActionPhase() && actions > 0) {
            actions--;
            game.gameLog("actions: " + actions);

            hand.remove(card);

            if (card instanceof SupportReaction) {
                game.gameLog(this.getPlayerName() + " played " + card.getName());
                if (card instanceof ExpertMechTechs) {
                    expertMechTechsInDeploymentZone = true;
                } else if (card instanceof ForwardBase) {
                    forwardBaseInDeploymentZone = true;
                }
            } else if (card instanceof Support || card instanceof OverrunSupport) {
                game.gameLog(this.getPlayerName() + " played " + card.getName());
                addCardToPlayArea(card);
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
        if (unit instanceof MechUnit || unit instanceof VehicleUnit) {
            for (Card card : opponent.getDeploymentZone()) {
                if (card instanceof ActiveProbe) {
                    addGameLog(playerName + " gets to draw 1 card since opponent deployed a Mech or Vehicle unit due to Active Probe ability on " + card.getName());
                    opponent.drawCards(1);
                }
            }
        }

        deploymentZone.add(unit);
    }

    public void useUnitAbility(Unit unit) {
        if (!unit.isAbilityUsed() && unit.isAbilityAvailable(this)) {
            unit.setAbilityUsed(true);
            unit.useUnitAbility(this);
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

        for (Card card : cardsPlayed) {
            if (card instanceof WarBonds) {
                addGameLog("War Bonds returned to supply");
            } else {
                addCardToDiscard(card);
            }
            cardRemovedFromPlay(card);
        }

        for (Card card : deploymentZone) {
            if (card instanceof MechUnit) {
                ((MechUnit) card).setAbilityUsed(false);
            }
        }

        cardsPlayed.clear();

        hand.stream().forEach(this::addCardToDiscard);
        hand.clear();

        drawCards(5);

        yourTurn = false;

        for (Card card : deploymentZone) {
            if (card instanceof TacticalCommand) {
                drawCards(2);
                if (hand.size() > 5) {
                    discardCardsFromHand(hand.size() - 5);
                }
            }
        }

        if (actionsQueue.isEmpty()) {
            game.turnEnded();
        }
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();

        cards.addAll(hand);
        cards.addAll(deck);
        cards.addAll(discard);
        cards.addAll(cardsPlayed);
        cards.addAll(deploymentZone);

        return cards;
    }

    public void scrapUnitFromDeploymentZone(Unit unit) {
        addGameLog(playerName + " scrapped " + unit.getName());
        deploymentZone.remove(unit);
        playerCardScrapped(unit);
        cardRemovedFromPlay(unit);
    }

    public void cardDamaged(Unit unit) {
        if (unit instanceof TotemMech && getNumMechUnitsInDeploymentZone() == 1) {
            addGameLog(playerName + "'s " + unit.getName() + " was not damaged due to Totem Mech ability");
            return;
        }

        addGameLog(playerName + " damaged " + unit.getName());
        deploymentZone.remove(unit);

        unit.unitDamaged(this);

        if (unit instanceof TacticalCommand) {
            addGameLog(opponent.getPlayerName() + " draws 2 cards from damaging " + unit.getName());
            opponent.drawCards(2);
        }

        if (unit instanceof MechUnit) {
            for (Card opponentCard : opponent.getDeploymentZone()) {
                if (opponentCard instanceof TargetingComputer) {
                    addGameLog(playerName + " gets to draw 1 card due to Targeting Computer ability on " + opponentCard.getName());
                    opponent.drawCards(1);
                }
            }
        }

        if (unit instanceof SoftTarget) {
            if (getOpponent().getNumMechUnitsInDeploymentZone() > 0) {
                playerCardScrapped(unit);
                cardRemovedFromPlay(unit);
                addGameLog(playerName + " scrapped " + unit.getName() + " due to Soft Target ability.");
            } else {
                addCardToDiscard(unit);
                cardRemovedFromPlay(unit);
            }
        } else if (unit instanceof MechUnit && expertMechTechsInDeploymentZone) {
            expertMechTechsInDeploymentZone = false;
            addGameLog(playerName + " scrapped Expert Mech Techs to put " + unit.getName() + " into their hand instead of their discard pile");
            addCardToHand(unit);
        } else {
            addCardToDiscard(unit);
            cardRemovedFromPlay(unit);
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

    public int getNumVehicleUnitsInDeploymentZone() {
        return (int) deploymentZone.stream().filter(c -> c instanceof VehicleUnit).count();
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

    public boolean isExpertMechTechsInDeploymentZone() {
        return expertMechTechsInDeploymentZone;
    }

    public boolean isForwardBaseInDeploymentZone() {
        return forwardBaseInDeploymentZone;
    }

    public void setForwardBaseInDeploymentZone(boolean forwardBaseInDeploymentZone) {
        this.forwardBaseInDeploymentZone = forwardBaseInDeploymentZone;
    }

    public Unit getUnitWithHighestIndustryCost(List<Unit> units) {
        if (units == null || units.isEmpty()) {
            return null;
        }
        List<Unit> sortedCards = units.stream().sorted((u1, u2) -> Integer.compare(u2.getIndustryCost(), u1.getIndustryCost())).collect(toList());
        return sortedCards.get(0);
    }

    public boolean isCardBuyable(Card card) {
        //noinspection SimplifiableIfStatement
        if (boughtInfantryPlatoonThisTurn && card instanceof InfantryPlatoon) {
            return false;
        }
        return yourTurn && isBuyPhase() && card.getIndustryCost() <= industry && (ignoreLosTechCost || card.getLosTechCost() <= losTech);
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
                points += 1;
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
        return hand.stream().anyMatch(Card::isResource);
    }

    public List<Card> getCardsPlayed() {
        return cardsPlayed;
    }

    public List<Card> getHiddenBaseCards() {
        return hiddenBaseCards;
    }

    public boolean isIgnoreLosTechCost() {
        return ignoreLosTechCost;
    }
}
