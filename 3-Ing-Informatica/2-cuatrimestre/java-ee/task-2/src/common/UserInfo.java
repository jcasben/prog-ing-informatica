package src.common;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String id;
    private String nick;
    private boolean firstMessageReceived;
    private boolean usersListSent;

    public UserInfo() {
        this.firstMessageReceived = false;
    }

    public UserInfo(String id, String nick) {
        this.id = id;
        this.nick = nick;
        this.firstMessageReceived = false;
        this.usersListSent = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isFirstMessageReceived() {
        return firstMessageReceived;
    }

    public void setFirstMessageReceived(boolean firstMessageReceived) {
        this.firstMessageReceived = firstMessageReceived;
    }

    public boolean isUsersListSent() {
        return usersListSent;
    }

    public void setUsersListSent(boolean usersListSent) {
        this.usersListSent = usersListSent;
    }
}
