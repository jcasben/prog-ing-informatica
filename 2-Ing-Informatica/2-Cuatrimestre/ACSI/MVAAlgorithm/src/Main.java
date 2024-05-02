public class Main {

    double [] Vi = {};
    double [] Si = {};
    double Z = 0.0;
    double [] Ri = {0.0, 0.0};
    double Ui = 0.0;

    public static void main(String[] args) {

        for (int N = 1; N <= 10; N++) {
            solve(N);
        }
    }

    public static void solve(int n) {

    }

    public static double Ni(int n) {
         return 0;
    }

    public static double R(int n) {
        return 0;
    }

    public double X(int n) {
        return n/(Z + R(n));
    }
}
