package src.part2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Java FX applications that renders an OSM Map.
 *
 * @author jcasben
 */
public class OSMApp extends Application {

    public static void main(String[] args) {
        if (args.length < 1) System.out.println("Usage: OSMApp map.osm");
        else launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            List<String> params = this.getParameters().getRaw();
            OSMParser parser = new OSMParser();
            parser.parse(params.getFirst());

            Canvas canvas = new Canvas(800, 600);
            OSMRenderer renderer = new OSMRenderer(canvas, parser.getNodes(), parser.getWays());
            renderer.render();

            Pane root = new Pane();
            root.getChildren().add(canvas);
            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("OSM Map Rendering");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
