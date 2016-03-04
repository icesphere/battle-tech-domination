package org.smartreaction.battletechdomination.service;

import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.User;
import org.smartreaction.battletechdomination.model.cards.Card;
import org.smartreaction.battletechdomination.model.cards.overrun.CriticalHit;
import org.smartreaction.battletechdomination.model.cards.overrun.HeavyCasualties;
import org.smartreaction.battletechdomination.model.cards.overrun.RaidedSupplies;
import org.smartreaction.battletechdomination.model.cards.overrun.Retreat;
import org.smartreaction.battletechdomination.model.cards.resource.*;
import org.smartreaction.battletechdomination.model.cards.support.*;
import org.smartreaction.battletechdomination.model.cards.support.attack.*;
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
import java.util.Collections;
import java.util.List;

@Stateless
public class GameService {
    @EJB
    LoggedInUsers loggedInUsers;

    public Game createGame(User user1, User user2) {
        Game game = new Game();

        HumanPlayer player1 = new HumanPlayer(user1);
        HumanPlayer player2 = new HumanPlayer(user2);

        List<Player> players = new ArrayList<>(2);
        players.add(player1);
        players.add(player2);

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        for (Player player : players) {
            player.setGame(game);
        }

        Collections.shuffle(players);

        game.setPlayers(players);

        game.getCurrentPlayer().setFirstPlayer(true);

        setupCards(game);

        game.startGame();

        return game;
    }

    public void setupCards(Game game) {
        for (Player player : game.getPlayers()) {
            for (int i = 0; i < 8; i++) {
                player.addCardToDeck(new BasicFactory());
            }
            for (int i = 0; i < 2; i++) {
                player.addCardToDeck(new InfantryPlatoon());
            }
            player.setup();
            if (!player.isFirstPlayer()) {
                player.addCardToDiscard(new InfantryPlatoon());
            }
        }

        for (int i = 0; i < 8; i++) {
            game.getBasicFactories().add(new BasicFactory());
        }
        for (int i = 0; i < 7; i++) {
            game.getInfantryPlatoons().add(new InfantryPlatoon());
        }
        for (int i = 0; i < 12; i++) {
            game.getMunitionsFactories().add(new MunitionsFactory());
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
            game.getRetreats().add(new Retreat());
        }
        game.setSupply(getSupplyCards());
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

        cards.add(new BattleArmorSquad());
        cards.add(new BattleArmorSquad());

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

    public void autoMatchUser(User user) {
        //todo synchronize this
        if (user.getCurrentGame() != null) {
            return;
        }
        List<User> users = loggedInUsers.getUsersWaitingForAutoMatch();
        if (!users.isEmpty()) {
            User opponent = users.get(0);
            opponent.setAutoMatch(false);
            Game game = createGame(user, opponent);
            user.setCurrentGame(game);
            user.setAutoMatch(false);
        } else {
            user.setAutoMatch(true);
        }
    }
}
