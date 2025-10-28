package cat.uib.eps.gein.aas.designlab1.ex3;

import java.util.HashMap;
import java.util.Map;

class VolatileDataStore implements DataStore {

    private Map<String, String> store = new HashMap<>();

    public void connect(String url) {
        System.out.println("Skipping: no need to connect.");
    }

    public void save(String key, String value) {
        System.out.println("Saving (" + key + "," + value + ").");
        store.put(key, value);
    }

    public String read(String key) {
        System.out.println("Retrieving: " + key + ".");
        return store.get(key);
    }

    public void sendEmail(String address, String text) {
        throw new UnsupportedOperationException("sendEmail");
    }

    public void close() {
        System.out.println("Skipping: no need to close.");
    }
}
