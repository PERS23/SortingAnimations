package PERS23.SortingAnimations;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.TimeUnit;

/* Pivot is non randomized, just taken from the end. */
public class ClassicQuickSort extends SortingAlgorithm {
    @Override
    public void sort(SortingService context, List<Pair<Integer, Rectangle>> sortList, int delay) throws InterruptedException {
        quickSort(context, sortList, delay, 0, sortList.size() - 1);
    }

    /* Explanation for the inner while loops - You have to arrange the sub-sequence so that the values are on the proper
     * side of the pivot (which is the end value). So to do it in place you walk inward from both sides, stopping when
     * you encounter one out of place with respect to the pivot. When both walks are stopped, you can swap the found
     * values to put them on the correct side. You finally stop when the walks have crossed over, otherwise we would
     * begin swapping to the wrong side. */
    private void quickSort(SortingService context, List<Pair<Integer, Rectangle>> sequence, int delay, int a, int b) throws InterruptedException {
        if (compare(context, a, b) >= 0) return; // Sub array is trivially sorted

        int left = a, right = b - 1;
        int pivot = getKey(context, sequence, b); // Use element at the end of the array as the pivot

        while (compare(context, left, right) < 1) {
            // Scan until reaching value equal or larger than pivot (or right marker)
            while (compare(context, left, right) < 1 && compare(context, getKey(context, sequence, left), pivot) < 0) {
                left++;
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            // Scan until reaching value equal or smaller than pivot (or left marker)
            while (compare(context, left, right) < 1 && compare(context, getKey(context, sequence, right), pivot) > 0) {
                right--;
                TimeUnit.MILLISECONDS.sleep(delay);
            }

            if (compare(context, left, right) < 1) { // Indices did not strictly cross, so swap values and shrink range
                swap(context, sequence, left, right);
                left++;
                right--;
                TimeUnit.MILLISECONDS.sleep(delay);
            }
        }
        // Put pivot into its final place (currently marked by left index)
        swap(context, sequence, left, b);
        TimeUnit.MILLISECONDS.sleep(delay);
        // Recurse
        quickSort(context, sequence, delay, a, left - 1);
        quickSort(context, sequence, delay, left + 1, b);
    }
}
