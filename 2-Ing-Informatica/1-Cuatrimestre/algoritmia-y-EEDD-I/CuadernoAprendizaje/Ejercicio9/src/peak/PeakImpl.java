package peak;

import java.awt.Point;

/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 */
public class PeakImpl implements Peak {
    /**
     * Finds the peak element in an array following a pattern of increasing values
     * until a certain index and then decreasing values. The peak element is defined
     * as an element that is greater than its neighbors. If the peak is found, returns
     * a Point object containing the peak value and its index. If no peak is found,
     * returns an Exception because the array does not comply with the precondition.
     * <br>
     * <p>
     *     pre: E!i 0 < i < a.length-1 : a[i-1] < a[i] > a[i+1] &&
     *          ∀j 0 < j <= i: a[j-1] < a[j] &&
     *          ∀k i < k < a.length: a[k-1] < a[k]
     * </p>
     * <p>
     *     post: Point.getX() == a[Point.getY()] &&
     *           a[Point.getY()-1] < Point.getX() > a[Point.getY()+1]
     * </p>
     * Complexity: O(log(n)) because it is a binary search.[we do: (first + last) / 2]
     * @param a The array in which to find the peak element.
     * @return A Point object representing the peak element and its index.
     */
    @Override
    public Point peak(int[] a) throws Exception {
        int first = 1;
        int last = a.length -2;

        while (first <= last) {
            int center = (first + last) / 2;

            if (a[center - 1] < a[center] && a[center] > a[center + 1]) {
                return new Point(a[center], center);
            } else if (a[center - 1] < a[center]) {
                first = center + 1;
            } else { //a[center] > a[center+1]
                last = center - 1;
            }
        }
        // If no peak is found.
        throw new Exception("The array does not comply with the precondition");
    }
}
