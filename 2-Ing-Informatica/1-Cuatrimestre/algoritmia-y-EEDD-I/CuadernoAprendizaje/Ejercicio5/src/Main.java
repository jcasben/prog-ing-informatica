import personas.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Persona> personas = generarPersonas(100000);
        System.out.println("----------------LISTAR PERSONAS------------------");
        listarPersonas(personas);
        System.out.println("\n---------------LISTAR ESTUDIANTES---------------");
        listarEstudiantes(personas);
        System.out.println("\n---------------LISTAR PROFESORADO---------------");
        listarProfesorado(personas);
    }

    /**
     * Genera n objetos nuevos de la clase {@link Persona}.
     *
     * @param n numero de personas que tienen que generar
     * @return ArrayList<Persona> con las personas generadas
     */
    public static ArrayList<Persona> generarPersonas(int n) {
        ArrayList<Persona> personas = new ArrayList<>();
        Random ran = new Random();
        int tipoPers;
        for (int i = 0; i < n; i++) {
            tipoPers = ran.nextInt(2);
            switch (tipoPers) {
                case 0 -> personas.add(new Estudiante());
                case 1 -> personas.add(new Profesor());
            }
        }
        return personas;
    }

    /**
     * Ordena alfabéticamente en orden inverso una lista de {@link Persona}
     * por su nombre.
     * <p>
     *     P ≡ Todos los objetos de la lista deben ser instancias de la clase Persona
     *      result ∈ List[Persona]
     * </p>
     * <p>
     *     Q ≡ {
     *      ∀i 0 <= i < result.length - 1:
     *       result.get(i).getNombre()
     *       .compareTo(result.get(i+1).getNombre()) >= 0
     *       }
     * </p>
     * @param personas lista de personas
     */
    public static void listarPersonas(List<Persona> personas) {
        personas.sort(Comparator.comparing(Persona::getNombre).reversed());
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + personas.get(i));
        }
    }

    /**
     * Ordena alfabéticamente en orden inverso una lista de {@link Estudiante}
     * en base al atributo programa o nombre.
     * <p>
     * P ≡ Todos los objetos de la lista deben ser instancias de la clase Estudiante y
     *     la clave debe ser una referencia al getter del atributo que se va a utilizar como
     *     clave de ordenación.
     * </p>
     *
     * <p>
     * result ∈ List[Estudiante]
     * Q ≡ {
     *      ∀i 0 <= i < result.length - 1:
     *       result.get(i).clave
     *       .compareTo(result.get(i+1).clave) >= 0
     *       }
     * </p>
     * @param personas lista de estudiantes
     */
    public static void listarEstudiantes(List<Persona> personas) {
        List<Estudiante> estudiantes = new ArrayList<>();
        for (Persona persona: personas) {
            if(persona instanceof Estudiante) {
                estudiantes.add((Estudiante) persona);
            }
        }
        estudiantes.sort(Comparator.comparing(Estudiante::getPrograma).reversed());
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i));
        }
    }

    /**
     * Ordena alfabéticamente en orden inverso una lista de {@link Profesor}
     * en base al atributo área o nombre.
     * <p>
     * P ≡ Todos los objetos de la lista deben ser instancias de la clase Profesor y
     *     la clave debe ser una referencia al getter del atributo que se va a utilizar como
     *     clave de ordenación.
     * </p>
     *
     * <p>
     * result ∈ List[Profesor]
     * Q ≡ {
     *      ∀i 0 <= i < result.length - 1:
     *       result.get(i).clave
     *       .compareTo(result.get(i+1).clave) >= 0
     *       }
     * </p>
     * @param personas lista de personas
     */
    public static void listarProfesorado(List<Persona> personas) {
        List<Profesor> profesores = new ArrayList<>();
        for (Persona persona: personas) {
            if(persona instanceof Profesor) {
                profesores.add((Profesor) persona);
            }
        }
        profesores.sort(Comparator.comparing(Profesor::getAreaLab).reversed());
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + profesores.get(i));
        }
    }
}