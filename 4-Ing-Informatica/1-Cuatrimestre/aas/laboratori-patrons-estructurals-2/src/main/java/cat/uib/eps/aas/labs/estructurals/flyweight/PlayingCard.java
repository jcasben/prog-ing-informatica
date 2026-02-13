package cat.uib.eps.aas.labs.estructurals.flyweight;

/**
 * PlayingCard representa una carta en juego con su estado extrínseco.
 * Esta clase contiene el estado que varía entre instancias:
 * - El jugador propietario
 * - El daño recibido
 * 
 * El estado intrínseco (nombre, ataque, puntos de vida, armadura) 
 * está en la clase Card que es compartida (Flyweight).
 */
public class PlayingCard {
    
    private final Card card; // Estado intrínseco compartido (Flyweight)
    private final Player owner; // Estado extrínseco: propietario de la carta
    private int damageTaken; // Estado extrínseco: daño acumulado

    public PlayingCard(Card card, Player owner) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        this.card = card;
        this.owner = owner;
        this.damageTaken = 0;
    }

    public Card getCard() {
        return card;
    }

    public Player getOwner() {
        return owner;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damageTaken += damage;
    }

    public boolean isAlive() {
        return card.getRemainingHitPoints(this) > 0;
    }    @Override
    public String toString() {
        return String.format("%s tiene %s (PV: %d/%d, Daño recibido: %d)", 
            owner, 
            card.getName(), 
            card.getRemainingHitPoints(this),
            card.getHitPoints(),
            damageTaken);
    }
}
