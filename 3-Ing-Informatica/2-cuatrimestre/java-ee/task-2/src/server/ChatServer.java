package src.server;

import src.common.SerializationUtil;
import src.common.UserInfo;
import src.common.packages.CustomPackage;
import src.common.packages.MessagePackage;
import src.common.packages.PackageType;

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
    private static final int HEADER_SIZE = 4;

    private static final Logger LOGGER = Logger.getLogger(ChatServer.class.getName());

    private static final Map<SocketChannel, UserInfo> connectedClients = Collections.synchronizedMap(new HashMap<>());
    private static final Map<SocketChannel, ByteBuffer> partialData = new HashMap<>();

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
                        connectedClients.put(client, null);
                        partialData.put(client, ByteBuffer.allocate(HEADER_SIZE));
                        LOGGER.info("New client connected");

                        if (key.isWritable()) {

                        }
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        CustomPackage received = receivePackage(client);

                        if (received == null) continue;

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

    private CustomPackage receivePackage(SocketChannel client) {
        CustomPackage customPackage = null;
        ByteBuffer buffer = partialData.get(client);

        try {
            int readBytes = client.read(buffer);

            if (readBytes == -1) {
                connectedClients.remove(client);
                client.close();
                LOGGER.info("Client disconnected");

                return null;
            }

            if (buffer.position() >= HEADER_SIZE) {
                buffer.flip();
                int messageSize = buffer.getInt();
                buffer.compact();

                if (buffer.capacity() < messageSize + HEADER_SIZE) {
                    ByteBuffer newBuffer = ByteBuffer.allocate(messageSize + HEADER_SIZE);
                    newBuffer.put(buffer);
                    partialData.put(client, newBuffer);
                }

                if (buffer.position() >= messageSize) {
                    buffer.flip();
                    buffer.position(HEADER_SIZE);
                    byte[] messageBytes = new byte[messageSize];
                    buffer.get(messageBytes);

                    customPackage = (CustomPackage) SerializationUtil.deserialize(messageBytes);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return customPackage;
    }

    private static void sendPackage(CustomPackage customPackage, SocketChannel receiver) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(SerializationUtil.serialize(customPackage));
            receiver.write(buffer);
        } catch (IOException e) {
            LOGGER.warning("Error sending message to client");
            connectedClients.remove(receiver);
            try {
                receiver.close();
            } catch (IOException ioException) { /*Ignore*/ }
        }
    }
}

