/**
 * Clase que permite contruir un circulo. Hereda de la clase {@link Figura}
 * @author linkc
 * @author jcasben
 */
public class Circulo extends Figura{
    private double r;

    /**
     * Constructor de la clase Circulo
     * @param r radio del circulo
     */
    public Circulo(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    /**
     * Calculo del area de un circulo
     * @return el valor del área
     */
    @Override
    public double area() {
        return Math.pow(r, 2) * Math.PI;
    }

    /**
     * Calculo del perimetro de un circulo
     * @return el valor del perimetro
     */
    @Override
    public double perimetro() {
        return Math.PI * r * 2;
    }

    /**
     *
     * @param f objeto a comparar.
     * @return un valor positivo si el area del objeto propio es mayor,
     *                un valor negativo si es menor y 0 si son iguales.
     */
    @Override
    public int compareTo(Figura f) {
        return Double.compare(this.area(),f.area());
    }

    /**
     * Metodo toString para la clase {@link Circulo}
     * @return el objeto de la clase {@link Circulo} convertido a String
     */
    @Override
    public String toString(){
        return ("Circulo con radio: " + r + ", area = " + area() + " y perimetro: " + perimetro());
    }
}
