package main.window.panels;

import main.window.panels.adivinar.PanelAdivinar;
import main.window.panels.adivinar.PanelBotonesAdivinar;
import main.window.panels.creacion.PanelCreacion;
import main.window.panels.creacion.PanelNotas;
import main.window.panels.reproduccion.BotonReproduccion;
import main.window.panels.reproduccion.PanelReproduccion;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que contiene todas las partes de la interfaz.
 * @author jcasben
 */
public class PanelPrincipal extends JPanel {
    private final JSplitPane jsp;
    private final JSplitPane jsp2;

    private PanelCreacion visualizacion;

    public static final PanelPrincipal mainpanel = new PanelPrincipal();

    /**
     * Configura los {@link JSplitPane} que forman la interfaz. El constructor es privado porque se utliza una instancia
     * estática para realizar todas las operaciones.
     */
    private PanelPrincipal() {
        setLayout(new BorderLayout());
        jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new PanelUIB(), new TitlePanel());
        jsp.setEnabled(false);
        jsp.setDividerLocation(600);

        jsp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jsp, new PanelActividades());
        jsp2.setEnabled(false);
        jsp2.setDividerLocation(665);
        add(jsp2);
        setVisible(true);
    }

    /**
     * Devuelve la intancia de la clase.
     * @return instancia de la clase.
     */
    public static PanelPrincipal getInstance() {
        return mainpanel;
    }

    /**
     * Canvia la interfaz a la interfaz del apartado CREAR.
     */
    public void changeToCreate() {
        visualizacion = new PanelCreacion();
        jsp.setRightComponent(new PanelNotas(visualizacion));
        jsp.setLeftComponent(visualizacion);
        jsp.setDividerLocation(600);
    }

    /**
     * Canvia la interfaz a la interfaz del apartado INICIO.
     */
    public void changeToHome() {
        jsp.setLeftComponent(new PanelUIB());
        jsp.setRightComponent(new TitlePanel());
        jsp.setDividerLocation(600);
    }

    /**
     * Canvia la interfaz a la interfaz del apartado REPRODUCIR.
     */
    public void changeToReproduce() {
        PanelReproduccion panelReproduccion = new PanelReproduccion(visualizacion.getUsedNotes(), visualizacion.getIndex());
        jsp.setLeftComponent(panelReproduccion);
        jsp.setRightComponent(new BotonReproduccion(panelReproduccion));
        jsp.setDividerLocation(600);
    }

    /**
     * Canvia la interfaz a la interfaz de después de acabar la REPRODUCCIÓN.
     */
    public void changeEndReproduction() {
        jsp.setRightComponent(new TitlePanel());
        jsp.setDividerLocation(600);
    }

    /**
     * Canvia la interfaz a la interfaz del apartado REPRODUCIR apartado CREAR.
     */
    public void changeToGuess() {
        PanelAdivinar adivinar = new PanelAdivinar(visualizacion.getUsedNotes(), visualizacion.getIndex());
        jsp.setLeftComponent(adivinar);
        jsp.setRightComponent(new PanelBotonesAdivinar(adivinar));
        jsp.setDividerLocation(600);
    }
}