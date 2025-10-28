package cat.uib.eps.gein.aas.designlab1.ex2;

import cat.uib.eps.gein.aas.designlab1.ex2.shape.Rectangle;

public class Runner {

    public static void main(String[] args) {
        GeometryService service = new GeometryService();
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(5);
        service.computeAndPrintArea(rectangle);
    }
}
