
package cat.uib.eps.aas.composite;

public interface FSComponent {
    String name();
    long getSize();               // en bytes
    void printStructure(String indent);
}
