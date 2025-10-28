package cat.uib.eps.gein.aas.designlab1.ex2.shape;

public class Rectangle implements Shape {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    @Override
    public int area() {
        return width * height;
    }
}
