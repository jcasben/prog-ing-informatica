package src.part2.models;

import java.util.List;

/**
 * Represents a way of OpenStreetMap maps.
 *
 * @author jcasben
 */
public class OSMWay {
    private long id;
    private String type;
    private List<Long> nodeRefs;

    public OSMWay(long id, String type, List<Long> nodeRefs) {
        this.id = id;
        this.type = type;
        this.nodeRefs = nodeRefs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getNodeRefs() {
        return nodeRefs;
    }

    public void setNodeRefs(List<Long> nodeRefs) {
        this.nodeRefs = nodeRefs;
    }
}
