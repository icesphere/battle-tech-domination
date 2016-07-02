package org.smartreaction.battletechdomination.service;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Defeat;
import org.smartreaction.battletechdomination.model.cards.resource.*;
import org.smartreaction.battletechdomination.model.cards.support.*;
import org.smartreaction.battletechdomination.model.cards.support.attack.*;
import org.smartreaction.battletechdomination.model.cards.support.reaction.Ambush;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ExpertMechTechs;
import org.smartreaction.battletechdomination.model.cards.support.reaction.ForwardBase;
import org.smartreaction.battletechdomination.model.cards.unit.infantry.*;
import org.smartreaction.battletechdomination.model.cards.unit.mech.*;
import org.smartreaction.battletechdomination.model.cards.unit.vehicle.*;
import org.smartreaction.battletechdomination.model.players.HumanPlayer;
import org.smartreaction.battletechdomination.model.players.Player;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
public class GameService {
    private EventBus eventBus;

    private final static String GAME_CHANNEL = "/game/";

    private final Object matchUserLock = new Object();

    @EJB
    LoggedInUsers loggedInUsers;

    public Game createGame(User user1, User user2) {
        Game game = new Game();

        HumanPlayer player1 = new HumanPlayer(user1);
        HumanPlayer player2 = new HumanPlayer(user2);

        List<Player> players = new ArrayList<>(2);
        players.add(player1);
        players.add(player2);

        user1.setCurrentPlayer(player1);
        user1.setCurrentGame(game);

        user2.setCurrentPlayer(player2);
        user2.setCurrentGame(game);

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        addTestCards(player1, user1.getGameOptions().getCardsToTest());
        addTestCards(player2, user2.getGameOptions().getCardsToTest());

        players.stream().forEach(p -> p.setGame(game));

        Collections.shuffle(players);

        game.setPlayers(players);

        game.getCurrentPlayer().setFirstPlayer(true);

        game.gameLog("** Starting Game **");
        game.gameLog("Player 1: " + players.get(0).getPlayerName() + " - Player 2: " + players.get(1).getPlayerName());

        setupCards(game);

        game.startGame();

        return game;
    }

    private void addTestCards(Player player, String cardsToTest) {
        if (!StringUtils.isEmpty(cardsToTest)) {
            String[] cardsNames = cardsToTest.split(",");
            Arrays.stream(cardsNames).forEach(cardName -> {
                Card card = getCardByName(cardName);
                if (card != null) {
                    player.addCardToHand(card);
                }
            });
        }
    }

    public void setupCards(Game game) {
        for (Player player : game.getPlayers()) {
            for (int i = 0; i < 6; i++) {
                player.addCardToDeck(new Factory());
            }

            player.addCardToDeck(new InfantryPlatoon());
            player.addCardToDeck(new InfantryPlatoon());

            player.addCardToDeck(new SupplyDrop());
            player.addCardToDeck(new SupplyDrop());

            player.setup();
        }

        for (int i = 0; i < 8; i++) {
            game.getSupplyDrops().add(new SupplyDrop());
        }
        for (int i = 0; i < 7; i++) {
            game.getInfantryPlatoons().add(new InfantryPlatoon());
        }
        for (int i = 0; i < 12; i++) {
            game.getHeavyFactories().add(new HeavyFactory());
        }
        for (int i = 0; i < 6; i++) {
            game.getAdvancedFactories().add(new AdvancedFactory());
        }
        for (int i = 0; i < 6; i++) {
            game.getHeavyCasualties().add(new HeavyCasualties());
        }
        for (int i = 0; i < 5; i++) {
            game.getRaidedSupplies().add(new RaidedSupplies());
        }
        for (int i = 0; i < 4; i++) {
            game.getCriticalHits().add(new CriticalHit());
        }
        for (int i = 0; i < 3; i++) {
            game.getDefeats().add(new Defeat());
        }

        List<Card> supplyCards = getSupplyCards();
        supplyCards.addAll(getExpansion1Cards());
        supplyCards.addAll(getExpansion2Cards());

        Collections.shuffle(supplyCards);
        game.setSupply(supplyCards.subList(0, 40));

        game.addCardsToSupplyGrid(6);
    }

    public List<Card> getSupplyCards() {
        List<Card> cards = new ArrayList<>();

        cards.add(new ExpertMechTechs());

        cards.add(new ForwardBase());

        cards.add(new DropShip());
        cards.add(new DropShip());

        cards.add(new StripMining());

        cards.add(new AmmoDump());

        cards.add(new BattlefieldSalvage());

        cards.add(new CommandCouncil());

        cards.add(new ComStarEngineers());

        cards.add(new Convoy());

        cards.add(new HeavyIndustry());

        cards.add(new HiddenBase());

        cards.add(new HighCommand());

        cards.add(new LosTechCache());

        cards.add(new MartialLaw());

        cards.add(new Mobilization());

        cards.add(new Propaganda());

        cards.add(new Quartermaster());

        cards.add(new RapidDeployment());
        cards.add(new RapidDeployment());

        cards.add(new Reinforcements());
        cards.add(new Reinforcements());

        cards.add(new Refinery());

        cards.add(new SocialGenerals());

        cards.add(new StagingGround());

        cards.add(new SupplyRoute());

        cards.add(new TacticalRedeployment());

        cards.add(new ArrowIVBattery());

        cards.add(new CloseAirSupport());

        cards.add(new ForcedNegotiations());

        cards.add(new GuerrillaWarfare());

        cards.add(new LongTomBattery());

        cards.add(new StrategicBombing());

        cards.add(new TacticalNuke());

        cards.add(new ManticoreHeavyTank());
        cards.add(new ManticoreHeavyTank());

        cards.add(new MobileHQ());

        cards.add(new PartisanHeavyTank());
        cards.add(new PartisanHeavyTank());

        cards.add(new SchrekPPCCarrier());

        cards.add(new SRMCarrier());

        cards.add(new SturmfeurLRMTank());

        cards.add(new EliteElementals());
        cards.add(new EliteElementals());

        cards.add(new Elementals());
        cards.add(new Elementals());

        cards.add(new MechanizedInfantry());
        cards.add(new MechanizedInfantry());

        cards.add(new VeteranInfantry());

        cards.add(new Adder());
        cards.add(new Adder());

        cards.add(new Atlas());

        cards.add(new Awesome());

        cards.add(new Catapult());

        cards.add(new Centurion());
        cards.add(new Centurion());

        cards.add(new Cicada());
        cards.add(new Cicada());

        cards.add(new Commando());
        cards.add(new Commando());

        cards.add(new Daishi());

        cards.add(new Firestarter());
        cards.add(new Firestarter());

        cards.add(new Gladiator());

        cards.add(new Hunchback());

        cards.add(new Jenner());
        cards.add(new Jenner());

        cards.add(new KitFox());
        cards.add(new KitFox());

        cards.add(new Loki());

        cards.add(new MadCat());

        cards.add(new MadCatMkII());

        cards.add(new Marauder());

        cards.add(new Masakari());

        cards.add(new Orion());

        cards.add(new Quickdraw());

        cards.add(new Raven());

        cards.add(new ShadowCat());
        cards.add(new ShadowCat());

        cards.add(new ShadowHawk());

        cards.add(new Stalker());

        cards.add(new Stormcrow());

        cards.add(new Thor());

        cards.add(new Trebuchet());
        cards.add(new Trebuchet());

        cards.add(new Urbanmech());
        cards.add(new Urbanmech());

        cards.add(new Uziel());

        cards.add(new Victor());

        cards.add(new Vulture());

        cards.add(new Zeus());

        return cards;
    }

    public List<Card> getExpansion1Cards() {
        List<Card> cards = new ArrayList<>();

        cards.add(new BankingSector());
        cards.add(new BankingSector());

        cards.add(new BlackMarket());

        cards.add(new FanaticalLeader());

        cards.add(new InvasionFleet());

        cards.add(new BlackHawk());

        cards.add(new BlackJack());
        cards.add(new BlackJack());

        cards.add(new Fenris());

        cards.add(new Griffin());
        cards.add(new Griffin());

        cards.add(new Highlander());

        cards.add(new JagerMech());

        cards.add(new KingCrab());

        cards.add(new Koshi());
        cards.add(new Koshi());

        cards.add(new Locust());

        cards.add(new NovaCat());

        cards.add(new Spider());
        cards.add(new Spider());

        cards.add(new Thunderbolt());

        cards.add(new Wolverine());

        cards.add(new Cheetah());
        cards.add(new Cheetah());

        cards.add(new Slayer());
        cards.add(new Slayer());

        cards.add(new Stuka());

        cards.add(new Transit());
        cards.add(new Transit());

        return cards;
    }

    public List<Card> getExpansion2Cards() {
        List<Card> cards = new ArrayList<>();

        cards.add(new PrimusOfComstar());

        cards.add(new BehindEnemyLines());

        cards.add(new TrialOfPosition());

        cards.add(new Ambush());

        cards.add(new Crab());

        cards.add(new Dragon());

        cards.add(new Gargoyle());

        return cards;
    }

    public Card getCardByName(String cardName) {
        if (cardName == null) {
            return null;
        }

        cardName = cardName.replaceAll("\\s", "").toLowerCase();
        cardName = cardName.replaceAll("'", "");

        switch (cardName) {
            case "criticalhit":
            case "criticalhits":
                return new CriticalHit();
            case "heavycasualties":
                return new HeavyCasualties();
            case "raidedsupplies":
                return new RaidedSupplies();
            case "defeat":
            case "defeat!":
            case "defeats":
                return new Defeat();

            case "advancedfactory":
                return new AdvancedFactory();
            case "factory":
                return new Factory();
            case "dropship":
                return new DropShip();
            case "heavyfactory":
                return new HeavyFactory();
            case "stripmining":
                return new StripMining();
            case "supplydrop":
                return new SupplyDrop();

            case "arrowivbattery":
                return new ArrowIVBattery();
            case "closeairsupport":
                return new CloseAirSupport();
            case "forcednegotiations":
                return new ForcedNegotiations();
            case "guerrillawarfare":
                return new GuerrillaWarfare();
            case "longtombattery":
                return new LongTomBattery();
            case "strategicbombing":
                return new StrategicBombing();
            case "tacticalnuke":
                return new TacticalNuke();

            case "expertmechtechs":
                return new ExpertMechTechs();
            case "forwardbase":
                return new ForwardBase();

            case "ammodump":
                return new AmmoDump();
            case "battlefieldsalvage":
                return new BattlefieldSalvage();
            case "commandcouncil":
                return new CommandCouncil();
            case "comstarengineers":
                return new ComStarEngineers();
            case "convoy":
                return new Convoy();
            case "heavyindustry":
                return new HeavyIndustry();
            case "hiddenbase":
                return new HiddenBase();
            case "highcommand":
                return new HighCommand();
            case "lostechcache":
                return new LosTechCache();
            case "martiallaw":
                return new MartialLaw();
            case "mobilization":
                return new Mobilization();
            case "propaganda":
                return new Propaganda();
            case "quartermaster":
                return new Quartermaster();
            case "rapiddeployment":
                return new RapidDeployment();
            case "refinery":
                return new Refinery();
            case "reinforcements":
                return new Reinforcements();
            case "socialgenerals":
                return new SocialGenerals();
            case "stagingground":
                return new StagingGround();
            case "supplyroute":
                return new SupplyRoute();
            case "tacticalredeployment":
                return new TacticalRedeployment();

            case "eliteelementals":
                return new EliteElementals();
            case "elementals":
                return new Elementals();
            case "infantryplatoon":
                return new InfantryPlatoon();
            case "mechanizedinfantry":
                return new MechanizedInfantry();
            case "veteraninfantry":
                return new VeteranInfantry();

            case "adder":
                return new Adder();
            case "atlas":
                return new Atlas();
            case "awesome":
                return new Awesome();
            case "catapult":
                return new Catapult();
            case "centurion":
                return new Centurion();
            case "cicada":
                return new Cicada();
            case "commando":
                return new Commando();
            case "daishi":
                return new Daishi();
            case "firestarter":
                return new Firestarter();
            case "gladiator":
                return new Gladiator();
            case "hunchback":
                return new Hunchback();
            case "jenner":
                return new Jenner();
            case "kitfox":
                return new KitFox();
            case "loki":
                return new Loki();
            case "madcat":
                return new MadCat();
            case "madcatmkii":
                return new MadCatMkII();
            case "marauder":
                return new Marauder();
            case "masakari":
                return new Masakari();
            case "orion":
                return new Orion();
            case "quickdraw":
                return new Quickdraw();
            case "raven":
                return new Raven();
            case "shadowcat":
                return new ShadowCat();
            case "shadowhawk":
                return new ShadowHawk();
            case "stalker":
                return new Stalker();
            case "stormcrow":
                return new Stormcrow();
            case "thor":
                return new Thor();
            case "trebuchet":
                return new Trebuchet();
            case "urbanmech":
                return new Urbanmech();
            case "uziel":
                return new Uziel();
            case "victor":
                return new Victor();
            case "vulture":
                return new Vulture();
            case "zeus":
                return new Zeus();

            case "manticoreheavytank":
                return new ManticoreHeavyTank();
            case "mobilehq":
                return new MobileHQ();
            case "partisanheavytank":
                return new PartisanHeavyTank();
            case "schrekppccarrier":
                return new SchrekPPCCarrier();
            case "srmcarrier":
                return new SRMCarrier();
            case "sturmfeurlrmtank":
                return new SturmfeurLRMTank();

            case "operationscenter":
                return new OperationsCenter();
            case "scrapyard":
                return new Scrapyard();
            case "blackhawk":
                return new BlackHawk();
            case "blackjack":
                return new BlackJack();
            case "fenris":
                return new Fenris();
            case "griffin":
                return new Griffin();
            case "highlander":
                return new Highlander();
            case "jagermech":
                return new JagerMech();
            case "kingcrab":
                return new KingCrab();
            case "koshi":
                return new Koshi();
            case "locust":
                return new Locust();
            case "novacat":
                return new NovaCat();
            case "spider":
                return new Spider();
            case "thunderbolt":
                return new Thunderbolt();
            case "wolverine":
                return new Wolverine();
            case "cheetah":
                return new Cheetah();
            case "slayer":
                return new Slayer();
            case "stuka":
                return new Stuka();
            case "transit":
                return new Transit();
            case "bankingsector":
                return new BankingSector();
            case "blackmarket":
                return new BlackMarket();
            case "combatdrop":
                return new CombatDrop();
            case "commandcircuit":
                return new CommandCircuit();
            case "fanaticalleader":
                return new FanaticalLeader();
            case "invasionfleet":
                return new InvasionFleet();

            case "primusofcomstar":
                return new PrimusOfComstar();
            case "behindenemylines":
                return new BehindEnemyLines();
            case "trialofposition":
                return new TrialOfPosition();
            case "ambush":
            case "ambush!":
                return new Ambush();
            case "crab":
                return new Crab();
            case "dragon":
                return new Dragon();
            case "gargoyle":
                return new Gargoyle();

            default:
                return null;
        }
    }

    public void autoMatchUser(User user) {
        synchronized (matchUserLock) {
            if (user.getCurrentGame() != null) {
                return;
            }
            List<User> users = loggedInUsers.getUsersWaitingForAutoMatch();
            if (!users.isEmpty()) {
                User opponent = users.get(0);
                opponent.setAutoMatch(false);
                user.setAutoMatch(false);
                createGame(user, opponent);
                sendLobbyMessage(user.getUsername(), opponent.getUsername(), "game_started");
            } else {
                user.setAutoMatch(true);
            }
        }
    }

    public void sendLobbyMessage(String sender, String recipient, String message) {
        sendGameMessage(sender, recipient, "lobby", message);
    }

    public void sendLobbyMessageToAll(String sender, String message) {
        sendLobbyMessage(sender, "*", message);
    }

    public void refreshLobby(String sender) {
        sendLobbyMessageToAll(sender, "refresh_lobby");
    }

    public void sendGameMessage(String sender, String recipient, String channel, String message) {
        getEventBus().publish(GAME_CHANNEL + channel + "/" + recipient, sender + ":" + message);
    }

    EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = EventBusFactory.getDefault().eventBus();
        }
        return eventBus;
    }
}
