import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

class ServerSideClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String nick;
    private String id;

    public String getId() {
        return id;
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

    public void sendMessage(Packet packet) {
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            Packet firstMessage = (Packet) in.readObject();
            this.id = firstMessage.id();
            this.nick = firstMessage.nick();
            System.out.println("[INFO]: User " + this.nick + " with ID " + this.id + " joined the chat");
            ChatServer.broadcast(new Packet(PacketType.LOGIN, this.id, this.nick, null), this);

            while (true) {
                Packet message = (Packet) in.readObject();
                ChatServer.broadcast(message, this);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            ChatServer.removeHandler(this);
            ChatServer.broadcast(new Packet(PacketType.LOGOUT, this.id, this.nick, null), this);
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}