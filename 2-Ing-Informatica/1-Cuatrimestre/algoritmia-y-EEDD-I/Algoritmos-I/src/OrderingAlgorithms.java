public class OrderingAlgorithms {
    public <T extends Comparable<T>> void directInsertion(T[] t) {
        final int N = t.length;
        int j;
        T tmp;
        for (int i = 1; i < N; i++) {
            tmp = t[i];
            j = i + 1;
            while ((j >= 0) && (tmp.compareTo(t[j]) < 0)) {
                t[j + 1] = t[j];
                j--;
            }
            t[j + 1] = tmp;
        }
    }

    public <T extends Comparable<T>> void directSelection(T[] t) {
        final int N = t.length;
        T min;
        int iMin;
        for (int i = 0; i < N - 1; i++) {
            min = t[i];
            iMin = i;
            for (int j = i + 1; j < N; j++) {
                if (t[j].compareTo(min) < 0) {
                    min = t[j];
                    iMin = j;
                }
            }
            t[iMin] = t[i];
            t[i] = min;
        }
    }

    public <T extends Comparable<T>> void bubbleSort(T[] t) {
        final int N = t.length;
        T tmp;
        for (int i = 0; i < N - 1; i++) {
            for (int j = N - 2; j >= i; j--) {
                if (t[j + 1].compareTo(t[j]) < 0) {
                    tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                }
            }
        }
    }

    public <T extends Comparable<T>> void enhancedBubbleSort(T[] t) {
        final int N = t.length;
        T tmp;
        int i = 0;
        while (i < N - 1) {
            int lj = N;
            for (int j = N - 2; j >= i; j--) {
                if (t[j + 1].compareTo(t[j]) < 0) {
                    tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                }
                lj = j;
            }
            i = lj + 1;
        }
    }

    public <T extends Comparable<T>> void shakeSort(T[] t) {
        final int N = t.length;
        T tmp;
        int l = 0, r = N - 1;
        while (l < r) {
            int lj = r;
            for (int j = N - 2; j >= l; j--) {
                if (t[j + 1].compareTo(t[j]) < 0) {
                    tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                    lj = j;
                }
            }
            l = lj + 1;
            for (int j = l; j <= r - 1; j++) {
                if (t[j + 1].compareTo(t[j]) < 0) {
                    tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                    lj = j + 1;
                }
            }
            r = lj - 1;
        }
    }

    public <T extends Comparable<T>> int dichotomySearch(T[] t, T x) {
        int left = 0, right = t.length - 1;
        int center = (left + right) / 2;
        while (left < right && t[center].compareTo(x) != 0) {
            if (t[center].compareTo(x) < 0) {
                left = center + 1;
            } else {
                right = center - 1;
            }
            center = (left + right) / 2;
        }
        return (t[center].compareTo(x) == 0 ? center : -1);
    }
}
