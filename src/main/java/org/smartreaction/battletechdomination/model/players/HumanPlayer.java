package org.smartreaction.battletechdomination.model.players;

import org.smartreaction.battletechdomination.model.User;

public class HumanPlayer extends Player {
    private User user;

    public HumanPlayer(User user) {
        this.user = user;
        playerName = user.getUsername();
    }

    public User getUser() {
        return user;
    }
}
