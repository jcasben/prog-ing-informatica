package src.server;

import src.common.UserInfo;
import src.common.packages.CustomPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server for a chat developed used Java.IO Sockets
 * @author jcasben
 */
public class ChatServer {
    public static final int PORT = 9999;
    private static final List<ServerSideClientHandler> clientHandlers = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(ChatServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.log(Level.INFO, "Server running on port " + PORT + "...");
            // For each new connection, it creates a new handler for that connection in a new thread.
            while (true) {
                Socket socket = serverSocket.accept();
                ServerSideClientHandler handler = new ServerSideClientHandler(socket);
                synchronized (clientHandlers) {
                    clientHandlers.add(handler);
                }
                new Thread(handler).start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "The following exception occurred (" + e.getMessage() + ").");
        }
    }

    /**
     * Broadcasts a {@link CustomPackage} to all the connected clients but the sender client.
     * @param customPackage package to be sent.
     * @param sender the client that wants to broadcast the message.
     */
    public static void broadcast(CustomPackage customPackage, ServerSideClientHandler sender) {
        synchronized (clientHandlers) {
            for (ServerSideClientHandler clientHandler : clientHandlers) {
                if (clientHandler != sender) {
                    clientHandler.sendMessage(customPackage);
                }
            }
        }
    }

    /**
     * Removes a handler from the server when a client disconnects.
     * @param handler handler to be removed.
     */
    public static void removeHandler(ServerSideClientHandler handler) {
        synchronized (clientHandlers) {
            clientHandlers.remove(handler);
        }
    }

    /**
     * Maps the handlers to an array of {@link UserInfo}, that is the object that represents
     * the main information about the user from that is connected with that handler.
     * @return the array of {@link UserInfo}
     */
    public static UserInfo[] connectedUsers() {
        return clientHandlers.stream().map(ServerSideClientHandler::getUser).toArray(UserInfo[]::new);
    }
}

