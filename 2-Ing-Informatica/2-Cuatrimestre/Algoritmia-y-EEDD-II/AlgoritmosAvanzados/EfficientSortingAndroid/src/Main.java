public class Main {
    public static void printArray(int [] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static int[] generateRandomArray(int size) {
        int [] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = generateRandomArray(600000);
        long start = System.currentTimeMillis();
        Sorting.selectionSort(array);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
    }
}
