package cat.uib.eps.aas.labs.creacionals.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro de prototipos que permite obtener clones de mobs predefinidos.
 * Respeta el principio Open/Closed: abierto para extensión (añadir nuevos prototipos)
 * pero cerrado para modificación.
 */
public class MobRegistry {
    
    private final Map<String, Prototype<Mob>> prototypes = new HashMap<>();
    
    /**
     * Registra un nuevo prototipo de mob.
     * @param key identificador único del prototipo
     * @param prototype el prototipo a registrar
     */
    public void registerPrototype(String key, Prototype<Mob> prototype) {
        prototypes.put(key, prototype);
    }
    
    /**
     * Obtiene un clon del prototipo registrado con la clave especificada.
     * @param key identificador del prototipo
     * @return un clon del prototipo, o null si no existe
     */
    public Mob getMob(String key) {
        Prototype<Mob> prototype = prototypes.get(key);
        return prototype != null ? prototype.clone() : null;
    }
    
    /**
     * Verifica si existe un prototipo registrado con la clave especificada.
     * @param key identificador del prototipo
     * @return true si existe, false en caso contrario
     */
    public boolean hasPrototype(String key) {
        return prototypes.containsKey(key);
    }
}
