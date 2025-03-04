package src.server;

import src.common.Packet;
import src.common.PacketType;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerSideClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private String nick;
    private String id;

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
        } catch (EOFException eof) {
            System.out.println("[INFO]: User " + this.nick + " with id " + this.id + " disconnected");
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
