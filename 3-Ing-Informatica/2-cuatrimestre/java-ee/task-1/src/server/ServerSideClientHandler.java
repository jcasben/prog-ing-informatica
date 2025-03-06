package src.server;

import src.common.packages.*;
import src.common.UserInfo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler of a client for the server of the Chat
 *
 * @author jcasben
 */
public class ServerSideClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private UserInfo user;

    private static final Logger LOGGER = Logger.getLogger(ServerSideClientHandler.class.getName());

    public UserInfo getUser() {
        return user;
    }

    public ServerSideClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a {@link CustomPackage} to the associated client.
     *
     * @param customPackage the package to be sent.
     */
    public void sendPackage(CustomPackage customPackage) {
        try {
            out.writeObject(customPackage);
            out.flush();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "The following exception occurred (" + e.getMessage() + ").");
        }
    }

    @Override
    public void run() {
        try {
            LoginPackage firstMessage = (LoginPackage) in.readObject();
            this.user = firstMessage.user;

            LOGGER.log(Level.INFO, "User " + this.user.nick() + " with ID " + this.user.id() + " joined the chat");
            ChatServer.broadcast(new LoginPackage(PackageType.LOGIN, this.user), this);

            sendPackage(new UserListPackage(PackageType.USER_LIST, ChatServer.connectedUsers()));

            while (true) {
                CustomPackage message = (CustomPackage) in.readObject();
                ChatServer.broadcast(message, this);
            }
        } catch (EOFException eof) {
            LOGGER.log(Level.INFO, "User " + this.user.nick() + " with ID " + this.user.id() + " disconnected");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "The following exception occurred (" + e.getMessage() + ").");
        } finally {
            ChatServer.removeHandler(this);
            ChatServer.broadcast(new LogoutPackage(PackageType.LOGOUT, this.user), this);
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "The following exception occurred (" + e.getMessage() + ").");
            }
        }
    }
}
