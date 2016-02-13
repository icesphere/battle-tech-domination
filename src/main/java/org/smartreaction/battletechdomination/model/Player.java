package org.smartreaction.battletechdomination.model;

import org.smartreaction.battletechdomination.model.cards.*;
import org.smartreaction.battletechdomination.model.cards.actions.Action;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.resource.MunitionsFactory;

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

    private int actions;
    private int attack;
    private int defense;
    private int industry;
    private int losTech;

    private int turns;

    private Player opponent;

    private Game game;

    private int shuffles;

    private boolean firstPlayer;

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
        deck.addAll(discard);
        discard.clear();
        shuffles++;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void addCardToTopOfDeck(Card card) {
        deck.add(0, card);
        addGameLog(card.getName() + " added to top of deck");
    }

    private void cardAcquired(Card card) {
        discard.add(card);
    }

    public void discardCardFromHand(Card card) {
        hand.remove(card);
        discard.add(card);
        addGameLog("Discarded " + card.getName() + " from hand");
    }

    public void opponentDiscardsCard() {
        addGameLog("Opponent discarding card");
        opponent.discardCardFromHand();
    }

    public void discardTopCardOfDeck() {
        Card card = deck.remove(0);
        addGameLog("Discarded " + card.getName() + " from top of deck");
        discard.add(card);
    }

    public void discardCardFromHand() {
        discardCardsFromHand(1, false);
    }

    public List<Card> discardCardsFromHand(int cards, boolean optional) {
        List<Card> cardsToDiscard = getCardsToDiscard(cards, optional);

        for (Card card : cardsToDiscard) {
            discardCardFromHand(card);
        }

        return cardsToDiscard;
    }

    public abstract List<Card> getCardsToDiscard(int cards, boolean optional);

    public abstract List<Card> getCardsFromHandToAddToTopOfDeck(int cards);

    public abstract void makeChoice(Card card, Choice... choices);

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
            cardAcquired(munitionsFactory);
        }
    }

    public void gainHeavyCasualties() {
        if (!game.getHeavyCasualties().isEmpty()) {
            HeavyCasualties heavyCasualties = game.getHeavyCasualties().remove(0);
            cardAcquired(heavyCasualties);
        }
    }

    public void gainRaidedSupplies() {
        if (!game.getRaidedSupplies().isEmpty()) {
            RaidedSupplies raidedSupplies = game.getRaidedSupplies().remove(0);
            cardAcquired(raidedSupplies);
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
        //todo what happens if there are less than 3 revealed cards?
        List<Card> cardsToDiscard = chooseCardsToDiscardForStrategicBombing();
        for (Card card : cardsToDiscard) {
            //todo
        }
    }

    public abstract List<Card> chooseCardsToDiscardForStrategicBombing();

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

    public void scrapCardFromHand(CardType cardType, boolean optional) {
        if (!hand.isEmpty()) {
            Card card = getCardToScrapFromHand(cardType, optional);
            if (card != null) {
                scrapCardFromHand(card);
            }
        }
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

    public void setupTurn() {
        actions = 2;
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
            } else if (card instanceof Unit || card instanceof SupportReaction) {
                deploymentZone.add(card);
            }

            card.cardPlayed(this);
        }
    }

    public void endTurn() {
        addGameLog("Ending turn");

        turns++;

        actions = 0;
        attack = 0;
        defense = 0;
        industry = 0;
        losTech = 0;

        for (Card card : resourcesPlayed) {
            discard.add(card);
            cardRemovedFromPlay(card);
        }

        for (Card card : supportCardsPlayed) {
            discard.add(card);
            cardRemovedFromPlay(card);
        }

        resourcesPlayed.clear();
        supportCardsPlayed.clear();

        discard.addAll(hand);
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
        card.cardDamaged(this);
        addGameLog("Damaged " + card.getName());
        deploymentZone.remove(card);
        discard.add(card);
        cardRemovedFromPlay(card);
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

    public int getDefense() {
        return defense;
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
}
