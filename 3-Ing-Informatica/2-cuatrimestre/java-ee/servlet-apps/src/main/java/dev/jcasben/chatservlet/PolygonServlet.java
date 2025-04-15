package dev.jcasben.chatservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "polygonServlet", value = "/polygon")
public class PolygonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pointsParam = req.getParameter("coordinates"); // e.g. "100,100;200,100;150,200"
        String[] coords = pointsParam.split(";");
        int[] xPoints = new int[coords.length];
        int[] yPoints = new int[coords.length];

        for (int i = 0; i < coords.length; i++) {
            String[] xy = coords[i].split(",");
            xPoints[i] = Integer.parseInt(xy[0].trim());
            yPoints[i] = Integer.parseInt(xy[1].trim());
        }

        double area = calculatePolygonArea(xPoints, yPoints);

        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = image.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, 500, 500);

        gr.setColor(Color.BLUE);

        final int SCALE_FACTOR = 50;
        final int MARGIN = 30;

        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] = (xPoints[i] * SCALE_FACTOR) + MARGIN;
            yPoints[i] = (yPoints[i] * SCALE_FACTOR) + MARGIN;

            gr.drawOval(xPoints[i] - 5, yPoints[i] - 5, 10, 10);
        }

        gr.drawPolygon(xPoints, yPoints, xPoints.length);

        gr.setColor(Color.RED);
        gr.drawString(String.format("Area: %.2f", area), 20, 20);

        OutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.close();
    }

    private double calculatePolygonArea(int[] x, int[] y) {
        double area = 0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += (x[i] * y[j]) - (x[j] * y[i]);
        }
        return Math.abs(area / 2.0);
    }
}
