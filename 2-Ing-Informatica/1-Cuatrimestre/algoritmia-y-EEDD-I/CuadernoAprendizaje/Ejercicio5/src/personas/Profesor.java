package personas;

/**
 * Clase que representa a un profesor, que hereda de la clase Persona.
 */
public class Profesor extends Persona {
    private int sueldo;
    private Area areaLab;

    /**
     * Constructor de la clase Profesor que inicializa sus atributos de forma aleatoria.
     */
    public Profesor() {
        super();
        this.areaLab = genRanArea(); // Genera un área de laboratorio aleatoria.
        this.sueldo = genRanSueldo(); // Genera un sueldo aleatorio.
    }

    /**
     * Genera un sueldo aleatorio para el profesor.
     *
     * @return El sueldo aleatorio del profesor.
     */
    private int genRanSueldo() {
        return RAN.nextInt(1900, 2601);
    }

    /**
     * Genera un área de laboratorio aleatoria para el profesor.
     *
     * @return El área de laboratorio aleatoria del profesor.
     */
    private Area genRanArea() {
        return Area.values()[RAN.nextInt(Area.values().length)];
    }

    /**
     * Enumeración que define las áreas a las que puede pertenecer un profesor.
     */
    public enum Area {
        INGENIERIA,
        CIENCIAS_SALUD,
        CIENCIAS_JURIDICAS,
        ARTES_HUMANIDADES
    }

    /**
     * Obtiene el sueldo del profesor.
     *
     * @return El sueldo del profesor.
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * Establece el sueldo del profesor.
     *
     * @param sueldo El sueldo del profesor.
     */
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Obtiene el área de laboratorio a la que pertenece el profesor.
     *
     * @return El área de laboratorio del profesor.
     */
    public Area getAreaLab() {
        return areaLab;
    }

    /**
     * Establece el área de laboratorio del profesor.
     *
     * @param areaLab El área de laboratorio del profesor.
     */
    public void setAreaLab(Area areaLab) {
        this.areaLab = areaLab;
    }

    /**
     * Compara si dos objetos Profesor son iguales.
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
        Profesor profesor = (Profesor) o;
        return super.equals(profesor) &&
                this.areaLab == profesor.areaLab &&
                this.sueldo == profesor.sueldo;
    }

    /**
     * Obtiene una representación en cadena de la clase Profesor.
     *
     * @return Una cadena que representa la clase Profesor.
     */
    @Override
    public String toString() {
        return "personas.Profesor {" +
                super.toString() +
                ", Area laboral: " + areaLab +
                ", Sueldo: " + sueldo +
                '}';
    }
}
