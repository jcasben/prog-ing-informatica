public abstract class Figura implements Comparable<Figura> {

    public abstract double area();
    public abstract double perimetro();

    @Override
    public int compareTo(Figura f) {
        return (int) (this.area() - f.area());
    }
}
