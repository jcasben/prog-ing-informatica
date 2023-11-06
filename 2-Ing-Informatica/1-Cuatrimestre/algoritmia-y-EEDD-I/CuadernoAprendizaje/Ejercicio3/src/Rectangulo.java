/**
 * Clase que permite construir Rectangulos. Hereda de la clase {@link Figura}
 */
public class Rectangulo extends Figura{
    private double h;
    private double b;

    /**
     * Permite crear instancias de la clase Rectangulo
     * @param h
     * @param b
     */
    public Rectangulo(double h, double b) {
        this.h = h;
        this.b = b;
    }

    public double getH() {
        return h;
    }

    public double getB() {
        return b;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setB(double b) {
        this.b = b;
    }

    /**
     * Calculo del area del rectangulo
     * @return valor del area del rectangulo
     */
    @Override
    public double area() {
        return b * h;
    }

    /**
     * Calculo del perimetro de un rectangulo
     * @return valor del perimetro de un rectangulo
     */
    @Override
    public double perimetro() {
        return (b * 2) + (h * 2);
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
     * Metodo toString para la clase {@link Rectangulo}
     * @return el objeto de la clase {@link Rectangulo} convertido a String
     */
    @Override
    public String toString(){
        return ("Rectangulo con base: " + b + ", altura: " + h + ", area = " + area() + " y perimetro: " + perimetro());
    }
}
