package src.client;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Record that stores a tuple of the elements requires in the UI for a client.
 *
 * @param nick     nickname of the user.
 * @param textArea text area where the message from this user will be displayed.
 * @author jcasben
 */
public record UIClientComponent(Label nick, TextArea textArea) {
}
