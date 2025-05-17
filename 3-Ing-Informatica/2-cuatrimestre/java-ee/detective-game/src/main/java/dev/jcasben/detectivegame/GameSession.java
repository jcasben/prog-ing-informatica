package dev.jcasben.detectivegame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameSession {
    private final String gameId = UUID.randomUUID().toString();
    private final String story;
    private final List<String> qaList = new ArrayList<>();
    private String authorId;
    private String detectiveId;
    private boolean gameFinished = false;

    public GameSession(String author, String story) {
        this.authorId = author;
        this.story = story;
    }

    public void addQA(String qa) {
        qaList.add(qa);
    }

    public String getMessages() {
        StringBuilder msgs = new StringBuilder();
        synchronized(qaList)
        {
            for (String m : qaList)
            {
                msgs.append(m);
                msgs.append("<br/>");
            }
            return msgs.toString();
        }
    }

    public String getGameId() {
        return gameId;
    }

    public String getStory() {
        return story;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(String detectiveId) {
        this.detectiveId = detectiveId;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
