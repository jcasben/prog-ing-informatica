import linkedList.LinkedList;

public class Main {
    public static void main(String[] args) {
        /*int [] array = generateInvertedArray(600000);
        long start = System.currentTimeMillis();
        Sorting.quickSort(array, 0, array.length - 1);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
         */
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(4);
        System.out.println(list);
        list.mergeSort();
        System.out.println(list);
    }

    private static int[] generateRandomArray(int n) {
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    private static int [] generateInvertedArray(int n) {
        int [] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = n - i;
        }
        return array;
    }
}
