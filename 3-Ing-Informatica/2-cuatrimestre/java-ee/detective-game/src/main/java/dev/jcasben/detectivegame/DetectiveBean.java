package dev.jcasben.detectivegame;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped()
public class DetectiveBean implements Serializable {
    private final String detectiveId = UUID.randomUUID().toString();
    private String question;
    private GameSession session;

    @Inject GameManager manager;

    public String joinGame() {
        session = manager.getLastWaitingGame();
        if (session != null) {
            session.setDetectiveId(detectiveId);
            return "detective.xhtml";
        }

        return "join-game.xhtml";
    }

    public void sendQuestion() {
        if (session != null && !session.isGameFinished()) {
            session.addQA("DETECTIVE: " + question);
            question = "";
        }
    }

    public void refresh() {}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public GameSession getSession() {
        return session;
    }

    public void setSession(GameSession session) {
        this.session = session;
    }
}
