package personas;

/**
 * Clase que representa a un estudiante, que hereda de la clase Persona.
 */
public class Estudiante extends Persona {

    private Programa programa;
    private Curso curso;
    private int cuota;

    /**
     * Constructor de la clase Estudiante que inicializa sus atributos.
     */
    public Estudiante() {
        super();
        this.programa = genRanProg(); // Genera un programa aleatorio.
        this.curso = genRanCurso(); // Genera un curso aleatorio.
        this.cuota = Persona.RAN.nextInt(800, 1501); // Genera una cuota aleatoria entre 800 y 1500.
    }

    private Curso genRanCurso() {
        return Curso.values()[Persona.RAN.nextInt(Curso.values().length)];
    }

    private Programa genRanProg() {
        return Programa.values()[Persona.RAN.nextInt(Programa.values().length)];
    }

    /**
     * Enumeración que define los programas a los que puede pertenecer un estudiante.
     */
    public enum Programa {
        INFORMATICA,
        INDUSTRIAL,
        MATEMATICAS,
        ENFERMERIA,
        PSICOLOGIA,
        HISTORIA,
        DERECHO
    }

    /**
     * Enumeración que define los cursos a los que puede pertenecer un estudiante.
     */
    private enum Curso {
        PRIMERO,
        SEGUNDO,
        TERCERO,
        CUARTO
    }

    /**
     * Obtiene el programa al que pertenece el estudiante.
     *
     * @return El programa del estudiante.
     */
    public Programa getPrograma() {
        return programa;
    }

    /**
     * Establece el programa al que pertenece el estudiante.
     *
     * @param programa El programa del estudiante.
     */
    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    /**
     * Obtiene el curso en el que se encuentra el estudiante.
     *
     * @return El curso del estudiante.
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Establece el curso en el que se encuentra el estudiante.
     *
     * @param curso El curso del estudiante.
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Obtiene la cuota que debe pagar el estudiante.
     *
     * @return La cuota del estudiante.
     */
    public int getCuota() {
        return cuota;
    }

    /**
     * Establece la cuota que debe pagar el estudiante.
     *
     * @param cuota La cuota del estudiante.
     */
    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    /**
     * Compara si dos objetos Estudiante son iguales.
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
        Estudiante estudiante = (Estudiante) o;
        return super.equals(o) &&
                this.programa == estudiante.programa &&
                this.curso == estudiante.curso &&
                this.cuota == estudiante.cuota;
    }

    /**
     * Obtiene una representación en cadena de la clase Estudiante.
     *
     * @return Una cadena que representa la clase Estudiante.
     */
    @Override
    public String toString() {
        return "personas.Estudiante {" +
                super.toString() +
                ", Programa: " + programa +
                ", Curso: " + curso +
                ", Cuota: " + cuota +
                '}';
    }
}
