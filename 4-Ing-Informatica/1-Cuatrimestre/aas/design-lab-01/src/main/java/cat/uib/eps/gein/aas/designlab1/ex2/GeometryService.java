package cat.uib.eps.gein.aas.designlab1.ex2;

import cat.uib.eps.gein.aas.designlab1.ex2.shape.Shape;

class GeometryService {

    public void computeAndPrintArea(Shape shape) {
        System.out.println("Area: " + shape.area());
    }
}
