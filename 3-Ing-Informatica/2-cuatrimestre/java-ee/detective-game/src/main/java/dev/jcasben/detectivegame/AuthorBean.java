package dev.jcasben.detectivegame;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
public class AuthorBean implements Serializable {
    private final String authorId = UUID.randomUUID().toString();
    private String name;
    private String story;
    private GameSession game;

    @Inject
    private GameManager manager;

    public String createGame() {
        game = new GameSession(authorId, story);
        manager.addGame(game);
        return "author.xhtml";
    }

    public void sayYes() {
        answerQuestion("Yes.");
    }

    public void sayNo() {
        answerQuestion("No.");
    }

    public void sayNoComments() {
        answerQuestion("No comments.");
    }

    public void sayThatsIt() {
        answerQuestion("That's it");
        game.setGameFinished(true);
    }

    public void answerQuestion(String answer) {
        if (game.getDetectiveId() != null && !game.isGameFinished()) {
            game.addQA("AUTHOR: " + answer);
        }
    }

    public void refresh() {}

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public GameSession getGame() {
        return game;
    }

    public void setGame(GameSession game) {
        this.game = game;
    }
}
