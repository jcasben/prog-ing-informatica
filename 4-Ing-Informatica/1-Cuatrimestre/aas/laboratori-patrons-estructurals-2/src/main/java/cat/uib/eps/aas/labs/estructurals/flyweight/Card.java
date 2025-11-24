package cat.uib.eps.aas.labs.estructurals.flyweight;

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

}
