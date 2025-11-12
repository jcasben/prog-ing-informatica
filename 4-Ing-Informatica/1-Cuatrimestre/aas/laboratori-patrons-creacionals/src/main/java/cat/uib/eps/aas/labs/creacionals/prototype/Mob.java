package cat.uib.eps.aas.labs.creacionals.prototype;

public class Mob implements Prototype<Mob> {

    public final int health;

    public final int armor;

    public final int damage;

    public final boolean passive;

    public Mob(int health, int armor, int damage, boolean passive) {
        System.out.println("Creating mob with health: " + health + " armor: " + armor + " damage: " + damage + " passive: " + passive);
        this.health = health;
        this.armor = armor;
        this.damage = damage;
        this.passive = passive;
    }

    // Constructor privado para clonación
    private Mob(Mob source) {
        System.out.println("Cloning mob with health: " + source.health + " armor: " + source.armor + " damage: " + source.damage + " passive: " + source.passive);
        this.health = source.health;
        this.armor = source.armor;
        this.damage = source.damage;
        this.passive = source.passive;
    }

    @Override
    public Mob clone() {
        return new Mob(this);
    }

}
