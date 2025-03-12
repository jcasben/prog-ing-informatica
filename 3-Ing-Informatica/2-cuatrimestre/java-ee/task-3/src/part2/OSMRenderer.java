package src.part2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import src.part2.models.OSMNode;
import src.part2.models.OSMWay;

import java.util.List;
import java.util.Map;

/**
 * Renders into a Java FX canvas the given list of nodes and ways.
 *
 * @author jcasben
 */
public class OSMRenderer {
    private final Canvas canvas;
    private final Map<Long, OSMNode> nodes;
    private final List<OSMWay> ways;
    private final double zoomFactor = 1;

    public OSMRenderer(Canvas canvas, Map<Long, OSMNode> nodes, List<OSMWay> ways) {
        this.canvas = canvas;
        this.nodes = nodes;
        this.ways = ways;
    }

    /**
     * Calculates the required scaling of the x coordinates to fit the canvas.
     *
     * @param lon    longitude coordinate.
     * @param minLon minimum longitude of the map.
     * @param maxLon maximum longitude of the map.
     * @return the scaled x coordinate.
     */
    private double scaleX(double lon, double minLon, double maxLon) {
        return (lon - minLon) * (canvas.getWidth() / (maxLon - minLon)) * zoomFactor;
    }

    /**
     * Calculates the required scaling of the x coordinates to fit the canvas.
     *
     * @param lat    latitude coordinate.
     * @param minLat minimum latitude of the map.
     * @param maxLat maximum latitude of the map.
     * @return the scaled y coordinate.
     */
    private double scaleY(double lat, double minLat, double maxLat) {
        return (maxLat - lat) * (canvas.getHeight() / (maxLat - minLat)) * zoomFactor;
    }

    /**
     * Draws all the nodes into the canvas given the existent ways.
     */
    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Find the bounding box of the area
        double minLat = Double.MAX_VALUE, maxLat = Double.MIN_VALUE;
        double minLon = Double.MAX_VALUE, maxLon = Double.MIN_VALUE;

        for (OSMWay way : ways) {
            for (Long nodeRef : way.getNodeRefs()) {
                OSMNode node = nodes.get(nodeRef);
                if (node != null) {
                    minLat = Math.min(minLat, node.getLatitude());
                    maxLat = Math.max(maxLat, node.getLatitude());
                    minLon = Math.min(minLon, node.getLongitude());
                    maxLon = Math.max(maxLon, node.getLongitude());
                }
            }
        }

        // Add padding to the bounding box to make the map smaller
        double padding = 0.1; // 10% padding around the map
        double latRange = maxLat - minLat;
        double lonRange = maxLon - minLon;
        minLat -= latRange * padding;
        maxLat += latRange * padding;
        minLon -= lonRange * padding;
        maxLon += lonRange * padding;

        // Render the ways
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(2);

        for (OSMWay way : ways) {
            if (!way.getNodeRefs().isEmpty()) {
                double[] xPoints = new double[way.getNodeRefs().size()];
                double[] yPoints = new double[way.getNodeRefs().size()];
                int index = 0;

                for (Long nodeRef : way.getNodeRefs()) {
                    OSMNode node = nodes.get(nodeRef);
                    if (node != null) {
                        xPoints[index] = scaleX(node.getLongitude(), minLon, maxLon);
                        yPoints[index] = scaleY(node.getLatitude(), minLat, maxLat);
                        index++;
                    }
                }
                gc.strokePolyline(xPoints, yPoints, index);
            }
        }
    }
}
