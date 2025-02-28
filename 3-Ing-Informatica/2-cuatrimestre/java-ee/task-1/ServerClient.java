import java.io.*;
import java.net.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServerClient extends Application implements Runnable {
    private static final long serialVersionUID = -4297335882692216363L;

    Socket socket;
    TextArea t1 = new TextArea();
    TextArea t2 = new TextArea();

    private static final int port = 2004;
    VBox root;
    Stage stage;

    OutputStream wr;
    InputStream rd;

    @Override
    public void start(Stage primaryStage) {
        root = new VBox();
        this.stage = primaryStage;
        root.getChildren().add(t1);
        root.getChildren().add(t2);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("ChatFx");
        primaryStage.setScene(scene);
        primaryStage.show();

        t1.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        t1.requestFocus();
        t1.setOnKeyReleased(x -> {
            sendMessage(t1.getText());
        });
        t2.setEditable(false);
        stage.setOnCloseRequest((evt) -> {
            try {
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });

        Thread th = new Thread( this);
        th.start();
    }

    public void run()
    {
        createConnection();

        while(acceptMessage());
        System.out.println("End of conversation");
        try {
            socket.close();
        } catch (Exception e) { e.printStackTrace(); }
        System.exit(0);
    }

    private void createConnection() {
        try {

            try {
                // first try to connect to other party, if it is already listening
                socket = new Socket("localhost", port);
                System.out.println("Created socket for sending data");
            } catch (ConnectException e) {
                // otherwise create a listening socket and wait for the other party to connect
                System.out.println("The other side is not ready yet. Waiting for connection...");
                ServerSocket srv = new ServerSocket(port);
                socket = srv.accept();
                System.out.println("Created socket for receiving data");
            }
            wr = socket.getOutputStream();
            rd = socket.getInputStream();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        byte bts[] = message.getBytes();
        try {
            wr.write(bts.length & 255);
            wr.write(bts.length >> 8);
            wr.write(bts, 0, bts.length);
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean acceptMessage() {
        try {
            int nbts = rd.read();
            int nbts2 = rd.read();
            if ((nbts < 0) || (nbts2 < 0)) return false;
            nbts = nbts + ( nbts2 << 8);
            byte bts[] = new byte[nbts];
            int i = 0; // how many bytes did we read so far
            do {
                int j = rd.read(bts, i, bts.length - i);
                if (j > 0) i += j;
                else break;
            } while (i < bts.length);
            t2.setText(new String(bts));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
