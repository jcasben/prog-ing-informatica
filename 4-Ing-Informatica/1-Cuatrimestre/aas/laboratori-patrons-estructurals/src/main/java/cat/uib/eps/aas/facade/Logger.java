
package cat.uib.eps.aas.facade;

public interface Logger {
    void info(String msg);
    void error(String msg, Throwable t);

    static Logger std() {
        return new Logger() {
            @Override public void info(String msg) { System.out.println("[INFO] " + msg); }
            @Override public void error(String msg, Throwable t) {
                System.err.println("[ERROR] " + msg);
                if (t != null) t.printStackTrace();
            }
        };
    }
}
