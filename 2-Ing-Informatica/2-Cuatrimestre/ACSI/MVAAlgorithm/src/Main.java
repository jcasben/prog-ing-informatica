public class Main {

    static double [] Vi = {8.0,7.0};
    static double [] Si = {0.03,0.1};
    static double Z = 8.0;
    static int N1 = 10;

    static double [] demandas = new double[2];
    static double DB;
    static double Nsat;
    static double D = 0;

    static double [][] Ri = new double[2][N1 + 1];
    static double [][] Ni = new double[2][N1 + 1];

    public static void main(String[] args) {

        System.out.println("------------------------------------------------------------------");
        System.out.println("| WORKS |    R1      R2    |    R       X0    |    N1      N2    |");
        System.out.println("------------------------------------------------------------------");
        for (int N = 1; N <= N1; N++) {
            solve(N);
        }
        System.out.println("------------------------------------------------------------------");
        demanda();
        saturacion();
        System.out.printf("\nDemanda CPU = %.4f\n", demandas[0]);
        System.out.printf("Demanda Disco = %.4f\n", demandas[1]);
        if (DB == demandas[0]) System.out.println("El cuello de botella lo genera la CPU");
        else System.out.println("El cuello de botella lo genera el Disco");
        System.out.printf("Tiempo de respuesta: %.4f\n", R(N1));
        System.out.printf("Tiempo total: %.4f\n", R(N1) + Z);
        System.out.println("Punto de saturación: " + Nsat);

        System.out.printf("\nUsuarios reflexionando: %.4f\n", N1 - R(N1) * X(N1));
        System.out.printf("Usuarios trabajando: %.4f\n", R(N1) * X(N1));
    }

    public static void solve(int user) {

        for (int n = 1; n <= user; n++) {
            for (int i = 0; i < Vi.length ; i++) {
                Ri[i][n] = (Ni[i][n - 1] + 1) * Si[i];
            }
            for (int i = 0; i < Vi.length ; i++) {
                Ni[i][n] = X(n) * Vi[i] * Ri[i][n];
            }
        }
        System.out.printf(
                "|   %d\t|  %.4f  %.4f  |  %.4f  %.4f  |  %.4f  %.4f  |\n",
                user, Ri[0][user], Ri[1][user], R(user), X(user), Ni[0][user], Ni[1][user]
        );
    }

    public static double R(int n) {
        double tmp = 0.0;
        for (int i = 0; i < Vi.length; i++) {
            tmp += Vi[i] * Ri[i][n];
        }
        return tmp;
    }

    public static double X(int n) {
        return n / (Z + R(n));
    }

    public static void demanda() {
        for (int i = 0; i < demandas.length; i++) {
            demandas[i] = Vi[i] * Si[i];
            D += demandas[i];
        }
        DB = demandas[0];
        for (int i = 1; i < demandas.length; i++) {
            DB = Math.max(DB, demandas[i]);
        }
    }

    public static void saturacion() {
        Nsat = Math.ceil((D + Z) / DB);
    }
}