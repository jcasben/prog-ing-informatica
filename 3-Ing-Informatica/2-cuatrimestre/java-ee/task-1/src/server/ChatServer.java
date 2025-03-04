package src.server;

import src.common.Packet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    public static final int PORT = 9999;
    private static final List<ServerSideClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server running on port " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                ServerSideClientHandler handler = new ServerSideClientHandler(socket);
                synchronized (clientHandlers) {
                    clientHandlers.add(handler);
                }
                new Thread(handler).start();
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public static void broadcast(Packet packet, ServerSideClientHandler sender) {
        synchronized (clientHandlers) {
            for (ServerSideClientHandler clientHandler : clientHandlers) {
                if (clientHandler != sender) {
                    clientHandler.sendMessage(packet);
                }
            }
        }
    }

    public static void removeHandler(ServerSideClientHandler handler) {
        synchronized (clientHandlers) {
            clientHandlers.remove(handler);
        }
    }
}

