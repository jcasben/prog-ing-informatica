package dev.jcasben.detectivegame;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class GameManager {
    private final List<GameSession> games = new ArrayList<>();

    public synchronized void addGame(GameSession game) {
        games.add(game);
    }

    public synchronized GameSession getLastWaitingGame() {
        for (GameSession game : games) {
            if (game.getDetectiveId() == null) return game;
        }
        return null;
    }

    public String navigateToCreateStory() {
        return "create-story.xhtml";
    }

    public String navigateToJoinGame() {
        return "join-game.xhtml";
    }
}
