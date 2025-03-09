package src.server;

import src.common.SerializationUtil;
import src.common.UserInfo;
import src.common.packages.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.Logger;

/**
 * Server for a chat developed used Java.NIO Sockets
 *
 * @author jcasben
 */
public class ChatServer {
    public static final int PORT = 9999;

    private static final Logger LOGGER = Logger.getLogger(ChatServer.class.getName());

    private static final Map<SocketChannel, UserInfo> connectedClients = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        new ChatServer().createConnection();
    }

    public void createConnection() {
        try (Selector selector = Selector.open()) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            LOGGER.info("NIO Server listening on port " + PORT + "...");

            while (true) {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        selector.wakeup();
                        connectedClients.put(client, new UserInfo());
                        LOGGER.info("New client connected");
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        UserInfo user = connectedClients.get(client);
                        CustomPackage received = receivePackage(client);

                        if (received == null) continue;

                        if (!user.isFirstMessageReceived()) {
                            LoginPackage login = (LoginPackage) received;
                            user.setFirstMessageReceived(true);
                            user.setId(login.user.getId());
                            user.setNick(login.user.getNick());
                            LOGGER.info("User info: " + user.getNick() + " " + user.getId());
                            if (!user.isUsersListSent()) {
                                sendPackage(new UserListPackage(PackageType.USER_LIST, connectedClients.values().toArray(UserInfo[]::new)), client);
                                user.setFirstMessageReceived(true);
                            }
                        }

                        broadcast(received, client);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void broadcast(CustomPackage customPackage, SocketChannel sender) {
        synchronized (connectedClients) {
            for (SocketChannel client : connectedClients.keySet()) {
                if (client != sender) {
                    sendPackage(customPackage, client);
                }
            }
        }
    }

    /**
     * Receives a package from a client and deserializes it into a {@link CustomPackage}
     *
     * @param client that sends the message
     * @return the {@link CustomPackage} received
     */
    private CustomPackage receivePackage(SocketChannel client) {
        ByteBuffer buffer = ByteBuffer.allocate(4096);

        try {
            int readBytes = client.read(buffer); // Read data into the buffer

            if (readBytes == -1) {
                connectedClients.remove(client);
                client.close();
                LOGGER.info("Client disconnected");
                return null;
            }

            LOGGER.info("Bytes read: " + readBytes);

            buffer.flip(); // Prepare the buffer for reading

            // Check if there is any data to process
            if (buffer.remaining() == 0) {
                buffer.compact(); // No data to read yet, so compact the buffer
                return null;
            }

            // Get the bytes from the buffer and process the message
            byte[] messageBytes = new byte[buffer.remaining()];
            buffer.get(messageBytes); // Get the remaining bytes
            buffer.clear(); // Clear the buffer for the next read

            // Deserialize the message
            return (CustomPackage) SerializationUtil.deserialize(messageBytes);
        } catch (IOException e) {
            //LOGGER.warning("Error receiving package: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    private static void sendPackage(CustomPackage customPackage, SocketChannel receiver) {
        try {
            byte[] packageBytes = SerializationUtil.serialize(customPackage);
            int packageSize = packageBytes.length;

            ByteBuffer buffer = ByteBuffer.allocate(packageSize);
            buffer.put(packageBytes);
            buffer.flip();

            while (buffer.hasRemaining()) {
                receiver.write(buffer);
            }
        } catch (IOException e) {
            LOGGER.warning("Error sending message to client: " + e.getMessage());
//            connectedClients.remove(receiver);
//            try {
//                receiver.close();
//            } catch (IOException ioException) { /*Ignore*/ }
        }
    }
}

