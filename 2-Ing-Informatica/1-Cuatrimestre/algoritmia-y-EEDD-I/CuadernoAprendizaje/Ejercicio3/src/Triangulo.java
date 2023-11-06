/**
 * Clase que permite contruir Triangulos. Hereda de la clase {@link Figura}
 * @author linkc
 * @author jcasben
 */
public class Triangulo extends Figura{
    private double b;
    private double h;

    /**
     * Permite contruir instancias de la clase Triangulo
     * @param base longitud de la base del triangulo
     * @param altura longitud de la altura del triangulo
     */
    public Triangulo(double base, double altura){
        this.b = base;
        this.h = altura;
    }

    /**
     * Calculo del area del triangulo
     * @return valor del area del triangulo
     */
    @Override
    public double area() {
        return (b*h)/2.0;
    }

    /**
     * Calculo del perimetro de un triangulo
     * @return valor del perimetro de un triangulo
     */
    @Override
    public double perimetro() {
        double lado = Math.sqrt(Math.pow(b/2,2) + Math.pow(h,2));
        return (2 * lado + b);
    }

    /**
     * Permite comparar dos objetos de la clase {@link Figura}
     * @param f objeto a ser comparado.
     * @return un valor positivo si el area del objeto propio es mayor,
     *          un valor negativo si es menor y 0 si son iguales.
     */
    @Override
    public int compareTo(Figura f) {
        return Double.compare(this.area(),f.area());
    }

    /**
     * Metodo toString para la clase {@link Triangulo}
     * @return el objeto de la clase {@link Triangulo} convertido a String
     */
    @Override
    public String toString(){
        return ("Trianculo con base: " + b + ", altura: " + h + ", area = " + area() + " y perimetro: " + perimetro());
    }
}
