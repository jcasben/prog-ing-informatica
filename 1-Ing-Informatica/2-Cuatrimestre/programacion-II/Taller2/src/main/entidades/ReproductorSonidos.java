package main.entidades;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Se encarga de reproducir los sonidos de las notas musicales, el sonido de error y el sonido de derrota.
 * @author jcasben
 */
public class ReproductorSonidos {
    /**
     * Reproduce el sonido seleccionado
     * @param note nombre del fichero de sonido seleccionado
     */
    public ReproductorSonidos(String note) {
        Clip clip;
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/" + note + ".wav")
                    .getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException error) {
            System.err.println("ERROR: PROBLEMAS CON LA REPRODUCCIÓN DEL SONIDO");
        }
    }
}
