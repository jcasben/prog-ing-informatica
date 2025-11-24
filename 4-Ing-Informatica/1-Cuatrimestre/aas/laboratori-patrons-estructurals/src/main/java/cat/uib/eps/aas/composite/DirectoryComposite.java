package cat.uib.eps.aas.composite;

import java.util.ArrayList;
import java.util.List;

public class DirectoryComposite implements FSComponent {

    private String name;
    private List<FSComponent> children = new ArrayList<>();

    public DirectoryComposite(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long getSize() {
        return children
                .stream()
                .mapToLong(FSComponent::getSize)
                .sum();
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "+ " + name);
        for (FSComponent child : children) {
            child.printStructure(indent + " ");
        }
    }

    public void add(FSComponent child) {
        children.add(child);
    }
}
