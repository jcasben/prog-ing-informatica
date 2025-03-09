package src.client;

import src.common.SerializationUtil;
import src.common.UserInfo;
import src.common.packages.*;
import src.server.ChatServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UI client for a chat developed used Java.IO Sockets
 *
 * @author jcasben
 */
public class ChatClient extends Application implements Runnable {

    private UserInfo user = new UserInfo(null, null);

    private SocketChannel socketChannel;

    private VBox root;
    private final TextArea t1 = new TextArea();

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private final HashMap<String, UIClientComponent> clientsUI = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(ChatClient.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Show a dialog to the user to introduce the nickname
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nickname");
        dialog.setHeaderText(null);
        dialog.setContentText("Please, introduce your name: ");
        String id = UUID.randomUUID().toString();
        Optional<String> result = dialog.showAndWait();
        result.ifPresentOrElse((name) -> user = new UserInfo(id, name), () -> user = new UserInfo(id, "Anonymous"));

        root = new VBox();
        String userLabelText = "You (" + user.getNick() + ")";
        root.getChildren().add(new Label(userLabelText));
        root.getChildren().add(t1);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Client - Chat");
        stage.setScene(scene);
        stage.show();

        t1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        t1.requestFocus();
        t1.setOnKeyReleased((e) -> {
            sendPackage(new MessagePackage(PackageType.MESSAGE, t1.getText(), this.user));
        });
        stage.setOnCloseRequest((e) -> {
            if (socketChannel != null) {
                sendPackage(new LogoutPackage(PackageType.LOGOUT, this.user));
                try {
                    socketChannel.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.WARNING, "The following exception occurred (" + ex.getMessage() + ").");
                }
            }
        });

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        if (!createConnection()) return;

        while (true) acceptMessage();

//        try {
//            socket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * Creates a connection with the server and sends a {@link LoginPackage} to
     * the server so it notifies the other clients.
     *
     * @return true if the connection is successful.
     */
    private boolean createConnection() {
//        try {
//            socket = new Socket("localhost", ChatServer.PORT);
//            LOGGER.log(Level.INFO, "Connected to server successfully");
//
//            out = new ObjectOutputStream(socket.getOutputStream());
//            in = new ObjectInputStream(socket.getInputStream());
//            sendPackage(new LoginPackage(PackageType.LOGIN, this.user));
//        } catch (ConnectException e) {
//            LOGGER.log(Level.WARNING, "Couldn't reach server. Shutting down...");
//            return false;
//        } catch (IOException e) {
//            LOGGER.log(Level.WARNING, "The following exception occurred (" + e.getMessage() + ").");
//            return false;
//        }
        // Java NIO implementation
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("localhost", ChatServer.PORT));
            socketChannel.configureBlocking(true);
            sendPackage(new LoginPackage(PackageType.LOGIN, this.user));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * Sends a {@link CustomPackage} to the server.
     *
     * @param customPackage the package to be sent.
     */
    private void sendPackage(CustomPackage customPackage) {
        try {
            byte[] packageBytes = SerializationUtil.serialize(customPackage);
            int packageSize = packageBytes.length;
            LOGGER.info("Package size: " + packageSize);

            ByteBuffer buffer = ByteBuffer.allocate(packageSize);
            buffer.put(packageBytes);
            buffer.flip();

            while (buffer.hasRemaining()) {
                int written = this.socketChannel.write(buffer);
                LOGGER.info("Written bytes: " + written);
            }
        } catch (IOException e) {
            LOGGER.warning("Error sending message to client");
        }
    }

    /**
     * Receives a {@link CustomPackage} from the server and performs the required action
     * depending on the {@link PackageType}.
     *
     * @return true if the client received a message.
     */
    private void acceptMessage() {
        ByteBuffer buffer = ByteBuffer.allocate(4096);

        try {
            int readBytes = socketChannel.read(buffer); // Read data into the buffer

            if (readBytes == -1) {
                LOGGER.info("Client disconnected");
            }

            buffer.flip(); // Prepare the buffer for reading

            // Check if there is any data to process
            if (buffer.remaining() == 0) {
                buffer.compact(); // No data to read yet, so compact the buffer
            }

            // Get the bytes from the buffer and process the message
            byte[] messageBytes = new byte[buffer.remaining()];
            buffer.get(messageBytes); // Get the remaining bytes
            buffer.clear(); // Clear the buffer for the next read

            // Deserialize the message
            CustomPackage incomingPackage = (CustomPackage) SerializationUtil.deserialize(messageBytes);

            switch (incomingPackage.type) {
                case MESSAGE -> {
                    MessagePackage messagePackage = (MessagePackage) incomingPackage;
                    Platform.runLater(() -> {
                        this.clientsUI.get(messagePackage.user.getId()).textArea().setText(messagePackage.message);
                    });
                }
                case LOGIN -> {
                    LoginPackage loginPackage = (LoginPackage) incomingPackage;
                    addClientToUI(loginPackage.user);
                    sendPackage(new MessagePackage(PackageType.MESSAGE, t1.getText(), this.user));
                }
                case LOGOUT -> {
                    LogoutPackage logoutPackage = (LogoutPackage) incomingPackage;
                    removeClientFromUI(logoutPackage.user.getId());
                }
                case USER_LIST -> {
                    UserListPackage listPackage = (UserListPackage) incomingPackage;
                    for (UserInfo user : listPackage.connectedUsers) {
                        if (!Objects.equals(user.getId(), this.user.getId())) {
                            addClientToUI(user);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.warning("Error receiving package: " + e.getMessage());
        }
    }

    /**
     * Adds the required element to the UI for a new user connects to the chat.
     *
     * @param user the connected user in the chat.
     */
    private void addClientToUI(UserInfo user) {
        Label label = new Label(user.getNick());
        TextArea textArea = new TextArea();
        Platform.runLater(() -> {
            root.getChildren().add(label);
            root.getChildren().add(textArea);
        });

        this.clientsUI.put(user.getId(), new UIClientComponent(label, textArea));
    }

    /**
     * Removes the UI of a client from the UI given its id when it disconnects.
     *
     * @param id the id of the disconnected user.
     */
    private void removeClientFromUI(String id) {
        UIClientComponent disconnectedClient = this.clientsUI.get(id);
        Platform.runLater(() -> {
            root.getChildren().remove(disconnectedClient.nick());
            root.getChildren().remove(disconnectedClient.textArea());
        });
        this.clientsUI.remove(id);
    }
}
