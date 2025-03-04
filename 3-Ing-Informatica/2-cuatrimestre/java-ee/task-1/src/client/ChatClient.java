package src.client;

import src.server.ChatServer;
import src.common.Packet;
import src.common.PacketType;
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
import java.net.Socket;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ChatClient extends Application implements Runnable {

    private Socket socket;
    private String nick;
    private String id;

    VBox root;
    Stage stage;
    TextArea t1 = new TextArea();

    ObjectOutputStream out;
    ObjectInputStream in;

    private HashMap<String, UIClientComponent> clientsUI = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nickname");
        dialog.setHeaderText(null);
        dialog.setContentText("Please, introduce your name: ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresentOrElse((name) -> nick = name, () -> nick = "Anonymous");

        root = new VBox();
        this.stage = stage;
        String userLabelText = "You (" + nick + ")";
        root.getChildren().add(new Label(userLabelText));
        root.getChildren().add(t1);

        id = UUID.randomUUID().toString();

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Client-Chat");
        stage.setScene(scene);
        stage.show();

        t1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        t1.requestFocus();
        t1.setOnKeyReleased((e) -> {
            sendMessage(new Packet(PacketType.MESSAGE, this.id, this.nick, t1.getText()));
        });
        stage.setOnCloseRequest((e) -> {
            if (socket != null) {
                notifyLogout();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        if (!createConnection()) return;

        while (acceptMessage()) ;

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean createConnection() {
        try {
            socket = new Socket("localhost", ChatServer.PORT);
            System.out.println("Connected to chat.server successfully");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            sendMessage(new Packet(PacketType.LOGIN, this.id, this.nick, null));
        } catch (ConnectException e) {
            System.err.println("Couldn't reach chat.server. Shutting down...");
            return false;
        } catch (IOException e) {
            System.err.println("An IO error occurred: " + e);
            return false;
        }

        return true;
    }

    private void sendMessage(Packet packet) {
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notifyLogout() {
        Packet outgoingPacket = new Packet(PacketType.LOGOUT, this.id, this.nick, null);
        try {
            out.writeObject(outgoingPacket);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean acceptMessage() {
        try {
            Packet incomingPacket = (Packet) in.readObject();
            switch (incomingPacket.type()) {
                case MESSAGE -> {
                    Platform.runLater(() -> {
                        this.clientsUI.get(incomingPacket.id()).textArea().setText(incomingPacket.message());
                    });
                }
                case LOGIN -> {
                    Label label = new Label(incomingPacket.nick());
                    TextArea textArea = new TextArea();
                    Platform.runLater(() -> {
                        root.getChildren().add(label);
                        root.getChildren().add(textArea);
                    });

                    this.clientsUI.put(incomingPacket.id(), new UIClientComponent(label, textArea));
                }
                case LOGOUT -> {
                    UIClientComponent disconnectedClient = this.clientsUI.get(incomingPacket.id());
                    Platform.runLater(() -> {
                        root.getChildren().remove(disconnectedClient.nick());
                        root.getChildren().remove(disconnectedClient.textArea());
                    });
                    this.clientsUI.remove(incomingPacket.id());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }

        return true;
    }
}
