package org.smartreaction.battletechdomination.service;

import org.smartreaction.battletechdomination.model.ChatMessage;
import org.smartreaction.battletechdomination.model.User;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Singleton
public class LoggedInUsers {
    List<User> users = new ArrayList<>();

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsersWaitingForAutoMatch() {
        return users.stream().filter(User::isAutoMatch).collect(toList());
    }

    public boolean isUsernameInUse(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
