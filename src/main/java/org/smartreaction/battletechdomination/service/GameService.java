package org.smartreaction.battletechdomination.service;

import org.smartreaction.battletechdomination.model.Game;
import org.smartreaction.battletechdomination.model.User;
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

        game.startGame();

        return game;
    }

    public void autoMatchUser(User user) {
        //todo synchronize this
        List<User> users = loggedInUsers.getUsersWaitingForAutoMatch();
        if (!users.isEmpty()) {
            User opponent = users.get(0);
            opponent.setAutoMatch(false);
            createGame(user, opponent);
        }
    }
}
