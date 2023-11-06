/**
 * Clase que permite construir un Cuadrado. Hereda de la clase {@link Rectangulo}
 * @author linkc
 * @author jcasben
 */
public class Cuadrado extends Rectangulo{
    /**
     * Constructor de la clase que permite crear nuevas instancias de Cuadrados.
     * @param lado longitud del lado
     */
    public Cuadrado(double lado) {
        super(lado,lado);
    }

    /**
     * Calculo del area del cuadrado
     * @return valor del area del cuadrado
     */
    @Override
    public double area() {
        double lado = super.getH();
        return lado*lado;
    }

    /**
     * Calculo del perimetro de un cuadrado
     * @return valor del perimetro de un cuadrado
     */
    @Override
    public double perimetro() {
        return 4*super.getH();
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
     * Metodo toString para la clase {@link Cuadrado}
     * @return el objeto de la clase {@link Cuadrado} convertido a String
     */
    @Override
    public String toString(){
        return ("Cuadrado con lado: " + super.getH() + ", area = " + area() + " y perimetro: " + perimetro());
    }
}
