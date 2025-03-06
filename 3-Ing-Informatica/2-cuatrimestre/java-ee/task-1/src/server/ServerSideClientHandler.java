package src.server;

import src.common.packages.CustomPackage;
import src.common.packages.LoginPackage;
import src.common.packages.LogoutPackage;
import src.common.packages.PackageType;
import src.common.UserInfo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private UserInfo user;

    public ServerSideClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(CustomPackage customPackage) {
        try {
            out.writeObject(customPackage);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            LoginPackage firstMessage = (LoginPackage) in.readObject();
            this.user = firstMessage.user;
            System.out.println("[INFO]: User " + this.user.nick() + " with ID " + this.user.id() + " joined the chat");
            ChatServer.broadcast(new LoginPackage(PackageType.LOGIN, this.user), this);

            while (true) {
                CustomPackage message = (CustomPackage) in.readObject();
                ChatServer.broadcast(message, this);
            }
        } catch (EOFException eof) {
            System.out.println("[INFO]: User " + this.user.nick() + " with id " + this.user.id() + " disconnected");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            ChatServer.removeHandler(this);
            ChatServer.broadcast(new LogoutPackage(PackageType.LOGOUT, this.user), this);
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
