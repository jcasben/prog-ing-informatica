package main.window.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel que contiene la imagen de fondo de la UIB.
 * @author jcasben
 */
public class PanelUIB extends JPanel {
    /**
     * Carga la imagen de la UIB en una {@link BufferedImage} y la introduce en un {@link JLabel}.
     */
    public PanelUIB() {
        setLayout(new BorderLayout());
        BufferedImage uibImage;

        try {
            uibImage = ImageIO.read(new File("resources/UIB.jpg"));
        } catch(IOException ioEx) {
            throw new RuntimeException(ioEx);
        }

        //Aquí se reescala la imagen para dejarla del tamaño deseado.
        JLabel uib = new JLabel(new ImageIcon(uibImage.getScaledInstance(800,600,Image.SCALE_DEFAULT)));
        add(uib);
        setVisible(true);
    }
}
