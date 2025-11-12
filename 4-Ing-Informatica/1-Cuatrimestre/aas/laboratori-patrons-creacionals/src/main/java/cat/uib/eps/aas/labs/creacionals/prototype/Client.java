package cat.uib.eps.aas.labs.creacionals.prototype;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        // Inicializar el registro de prototipos
        MobRegistry registry = initializeRegistry();
        
        // Crear mobs usando el registro (respetando DIP y OCP)
        System.out.println("\n=== Cloning Mobs from Registry ===");
        List<Mob> mobs = createMobs(registry);

        System.out.println("\n=== Total mobs created: " + mobs.size() + " ===");
    }

    /**
     * Inicializa el registro con los prototipos predefinidos.
     * Esta función está abierta para extensión: se pueden añadir nuevos prototipos
     * sin modificar el resto del código.
     */
    private static MobRegistry initializeRegistry() {
        System.out.println("=== Initializing Prototype Registry ===");
        MobRegistry registry = new MobRegistry();
        
        // Registrar prototipos predefinidos
        registry.registerPrototype("easy", new Mob(10, 10, 1, true));
        registry.registerPrototype("medium", new Mob(10, 20, 5, false));
        registry.registerPrototype("hard", new Mob(20, 30, 10, false));
        
        return registry;
    }

    /**
     * Crea una lista de mobs utilizando el registro de prototipos.
     * Depende de la abstracción (MobRegistry) en lugar de implementaciones concretas.
     * Respeta Single Responsibility: solo se encarga de crear la lista de mobs.
     */
    private static List<Mob> createMobs(MobRegistry registry) {
        List<Mob> mobs = new ArrayList<>();
        
        // Clonar 10 mobs fáciles
        for(int i = 0; i < 10; i++) {
            mobs.add(registry.getMob("easy"));
        }
        
        // Clonar 5 mobs medianos
        for(int i = 0; i < 5; i++) {
            mobs.add(registry.getMob("medium"));
        }
        
        // Clonar 2 mobs difíciles
        for(int i = 0; i < 2; i++) {
            mobs.add(registry.getMob("hard"));
        }
        
        return mobs;
    }
}
