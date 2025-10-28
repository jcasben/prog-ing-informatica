package cat.uib.eps.gein.aas.designlab1.ex3;

interface DataStore {
    void connect(String url);

    void save(String key, String value);

    String read(String key);

    void sendEmail(String address, String text);

    void close();
}
