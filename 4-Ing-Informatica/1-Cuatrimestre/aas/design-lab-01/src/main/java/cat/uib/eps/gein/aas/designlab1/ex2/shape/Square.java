package cat.uib.eps.gein.aas.designlab1.ex2.shape;

public class Square implements Shape {

    private int side;

    public Square(int side) {
        this.side = side;
    }

    public int area() {
        return side * side;
    }
}

