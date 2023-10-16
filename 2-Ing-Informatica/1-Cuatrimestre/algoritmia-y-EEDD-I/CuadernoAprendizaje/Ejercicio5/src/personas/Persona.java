package personas;

import java.util.Random;

/**
 * Clase que representa a una persona.
 */
public class Persona implements Comparable<Persona> {
    private String nombre;
    private String direccion;

    /**
     * Genera una instancia de la clase Random para la generación de datos aleatorios.
     */
    public static final Random RAN = new Random();

    /**
     * Constructor de la clase Persona que inicializa los atributos nombre y dirección con valores aleatorios.
     */
    public Persona() {
        this.nombre = generateRandomStrings();
        this.direccion = generateRandomStrings();
    }

    /**
     * Genera una cadena de caracteres aleatoria.
     *
     * @return Una cadena de caracteres aleatoria.
     */
    private String generateRandomStrings() {
        // El profesor dijo que para generar los nombres y las direcciones
        // podíamos crear Strings aleatorios.
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < RAN.nextInt(4, 8); i++) {
            name.append((char) RAN.nextInt(97, 123));
        }
        return name.toString();
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece la dirección de la persona.
     *
     * @param direccion La dirección de la persona.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la dirección de la persona.
     *
     * @return La dirección de la persona.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Compara si dos objetos Persona son iguales.
     *
     * @param o El objeto con el que se compara.
     * @return True si son iguales, False en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // Son la misma instancia
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Persona persona = (Persona) o;

        return this.nombre.equals(persona.nombre) &&
                this.direccion.equals(persona.direccion);
    }

    /**
     * Obtiene una representación en cadena de la clase Persona.
     *
     * @return Una cadena que representa la clase Persona.
     */
    @Override
    public String toString() {
        return (
                "Nombre: " + nombre +
                        ", Direccion: " + direccion
        );
    }

    /**
     * Compara los nombres de dos personas.
     *
     * @param p La persona a ser comparada.
     * @return Devuelve un valor negativo si el nombre de p es mayor,
     *         0 si los nombres son iguales y un valor positivo si el nombre de p es menor.
     */
    @Override
    public int compareTo(Persona p) {
        if (this.getNombre().length() < p.getNombre().length()) return -1;
        else if (this.getNombre().length() > p.getNombre().length()) return 1;
        else {
            for (int i = 0; i < this.getNombre().length(); i++) {
                if (this.getNombre().charAt(i) < p.getNombre().charAt(i)) return -1;
                else if (this.getNombre().charAt(i) > p.getNombre().charAt(i)) return 1;
            }
        }
        return 0;
    }
}
