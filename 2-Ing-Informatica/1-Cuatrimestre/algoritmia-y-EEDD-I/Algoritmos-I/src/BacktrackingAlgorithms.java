public class BacktrackingAlgorithms {

    //==============================================================================================
    // Recursive backtracking scheme
    //==============================================================================================
    int maxV = 10;
    public void recursiveBracktracking(int [] a) {
        int [] t = new int[a.length];
        System.out.println("recursiveBracktracking");
        recursiveBacktracking(a, t, maxV, 0);
    }

    public <E> void recursiveBacktracking(int [] a, int [] t, int maxV, int k) {
        t[k] = -1;
        while (t[k] < maxV) {
            t[k]++;
            if (!poda(a, t) && (k == a.length - 1)) {
                display(a, t);
            } else if (!poda(a, t) && (k < a.length - 1)) {
                recursiveBacktracking(a, t, maxV, k + 1);
            }
        }
    }

    //==============================================================================================
    // Iterative backtracking scheme
    //==============================================================================================
    public void iterativeBacktracking(int [] a) {
        int [] t = new int[a.length];
        for (int i = 0; i < t.length; i++) t[i] = -1;

        int k = 0;

        while (k >= 0) {
            if (t[k] < maxV) {
                t[k]++;
                if (!poda(a, t) && (k == a.length - 1)) {
                    display(a, t);
                } else if (!poda(a, t) && (k < a.length - 1)) {
                    k++;
                }
            } else {
                t[k] = -1;
                k--;
            }
        }
    }

    private boolean poda(int [] a, int [] t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void display(int [] a, int [] t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
