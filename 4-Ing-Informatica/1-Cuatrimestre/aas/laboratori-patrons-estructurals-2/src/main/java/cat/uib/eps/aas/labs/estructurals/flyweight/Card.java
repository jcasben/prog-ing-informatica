package cat.uib.eps.aas.labs.estructurals.flyweight;

/**
 * Card es el Flyweight que contiene el estado intrínseco compartido.
 * Este estado es inmutable y común a todas las instancias de una misma carta.
 * El estado extrínseco (propietario, daño recibido) está en PlayingCard.
 */
public class Card {

    private final String name;
    private final int attack;
    private final int hitPoints;
    private final int armor;
    private byte[] data; // Simulates some large data; the reason we want to share instances

    public Card(String name, int attack, int hitPoints, int armor) {
        this.name = name;
        this.attack = attack;
        this.hitPoints = hitPoints;
        this.armor = armor;
        this.data = new byte[1024];
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getArmor() {
        return armor;
    }

    /**
     * Calcula los puntos de vida restantes de una carta en juego.
     * Este método opera sobre el estado extrínseco (daño recibido)
     * y el estado intrínseco (puntos de vida totales).
     * 
     * @param playingCard La carta en juego con su estado extrínseco
     * @return Los puntos de vida restantes (no puede ser negativo)
     */
    public int getRemainingHitPoints(PlayingCard playingCard) {
        if (playingCard == null) {
            throw new IllegalArgumentException("PlayingCard cannot be null");
        }
        int remaining = this.hitPoints - playingCard.getDamageTaken();
        return Math.max(0, remaining);
    }

    /**
     * Realiza un ataque de una carta sobre otra.
     * El daño efectivo es: ataque del atacante - armadura del defensor (mínimo 1).
     * 
     * Este método respeta el principio de responsabilidad única (SRP):
     * La lógica de ataque está en Card, que es quien conoce los atributos de combate.
     * 
     * @param attacker La carta atacante (con su estado extrínseco)
     * @param target La carta objetivo (con su estado extrínseco)
     */
    public void attack(PlayingCard attacker, PlayingCard target) {
        if (attacker == null || target == null) {
            throw new IllegalArgumentException("Attacker and target cannot be null");
        }
        
        if (!attacker.isAlive()) {
            throw new IllegalStateException("Dead cards cannot attack");
        }
        
        if (!target.isAlive()) {
            throw new IllegalStateException("Cannot attack dead cards");
        }
        
        // El daño efectivo es el ataque menos la armadura, con un mínimo de 1
        int effectiveDamage = Math.max(1, this.attack - target.getCard().getArmor());
        target.takeDamage(effectiveDamage);
    }

}
